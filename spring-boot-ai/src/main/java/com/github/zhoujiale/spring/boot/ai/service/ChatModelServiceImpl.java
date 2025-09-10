package com.github.zhoujiale.spring.boot.ai.service;

import com.github.zhoujiale.spring.boot.ai.constants.PromptConstant;
import com.github.zhoujiale.spring.boot.ai.model.ChatSession;
import com.github.zhoujiale.spring.boot.ai.util.ChatMemoryUtil;
import com.github.zhoujiale.spring.boot.ai.util.ChatMethodUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.mcp.AsyncMcpToolCallbackProvider;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.template.st.StTemplateRenderer;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

/**
 * @classname: ChatModelServiceImpl
 * @author: zhou
 * @description:
 * @date: 2025/9/8 13:45
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatModelServiceImpl implements ChatModelService {

    private final ChatClient chatClient;

    private final OllamaChatModel ollamaChatModel;

    private final OllamaApi ollamaApi;

    private final AsyncMcpToolCallbackProvider asyncMcpToolCallbackProvider;


    @Override
    public Flux<OllamaApi.ChatResponse> ollamaChat(ChatSession chatSession) {
        /**
         * 如果要使用原生的ollama
         * 调用必须使用ollamaApi
         **/
        return ollamaApi.streamingChat(
                OllamaApi.ChatRequest.builder(ollamaChatModel.getDefaultOptions().getModel())
                        .options(OllamaOptions.builder()
                                .toolCallbacks()
                                .build())
                        .messages(
                                List.of(
                                        OllamaApi.Message.builder(OllamaApi.Message.Role.SYSTEM).content("你是一个风趣幽默的人工智能助手").build(),
                                        OllamaApi.Message.builder(OllamaApi.Message.Role.USER).content(chatSession.getMessage()).build()
                                )
                        )
                        .stream(true)
                        .build()).publishOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<Generation> chat(ChatSession chatSession) {
        //简易会话记忆窗口
        MessageChatMemoryAdvisor chatMemoryAdvisor = MessageChatMemoryAdvisor
                .builder(ChatMemoryUtil.getChatMemory(chatSession.getSessionId()))
                .conversationId(chatSession.getSessionId()).build();
        //用户角色模板
        final String userTemplate = "我当前身体不适，请在了解情况后提供医疗方案，下面是我的症状或我想询问的信息，{message}";
        return chatClient.prompt()
                .advisors(new SimpleLoggerAdvisor(), chatMemoryAdvisor)
                //用户模板增强
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{').endDelimiterToken('}').build())
                .user(u -> u.text(userTemplate).param("message", chatSession.getMessage()))
                //模板替换系统提示
                .system(s -> s.param(PromptConstant.SCOPE, chatSession.getScope()))
                .stream()
                .chatResponse()
                .map(ChatResponse::getResult);
    }

    @Override
    public Flux<Generation> methodChat(ChatSession chatSession) {
        return chatClient.prompt(Prompt.builder().content(chatSession.getMessage()).build())
                .advisors(new SimpleLoggerAdvisor())
                .tools(new ChatMethodUtil())
                .system(s -> s.param(PromptConstant.SCOPE, chatSession.getScope()))
                .stream()
                .chatResponse()
                .map(ChatResponse::getResult);
    }

    @Override
    public Flux<Generation> functionChat(ChatSession chatSession) {
        FunctionToolCallback<WeatherRequest, String> toolCallback = FunctionToolCallback
                .builder("weatherFunction", new WeatherService())
                .description("天气查询")
                .inputType(WeatherRequest.class)
                .build();
        return chatClient.prompt(Prompt.builder().content(chatSession.getMessage()).build())
                .advisors(new SimpleLoggerAdvisor())
                .toolCallbacks(toolCallback)
                .system(s -> s.param(PromptConstant.SCOPE, chatSession.getScope()))
                .stream()
                .chatResponse()
                .map(ChatResponse::getResult);
    }

    @Override
    public Flux<Generation> mcpChat(ChatSession chatSession) {
        ToolCallback[] toolCallbacks = asyncMcpToolCallbackProvider.getToolCallbacks();
        return chatClient.prompt()
                .advisors(new SimpleLoggerAdvisor())
                .toolCallbacks(toolCallbacks)
                .system(s -> s.text("""
                        你是一个<scope>领域的专家，拥有丰富的知识和经验。
                        如果用户询问路线规划，请按照以下规则处理。在思考过程中如何涉及和外部工具交互，请输出请求参数和响应结果，便于观测
                        1、获取起点和终点的地名，确定起点和终点的城市信息，分别换算成经纬度坐标，在思考过程中输出坐标，坐标格式经度在前，纬度在后，经度和纬度用","分割，经纬度小数点后不得超过6位。
                        2、选择公共交通工具的出行方式
                        3、根据起点和终点的经纬度坐标,城市信息，来选择不同的路线
                        4、向用户返回3条不同的路线，提供耗时、具体的交通工具（公共交通需要具体到几路车、几号地铁线）、具体的路线比如公共交通提供途径站点
                        """)
                        .param(PromptConstant.SCOPE, chatSession.getScope()))
                .user(u -> u.text("我当前在杭州，<task>")
                        .param("task", chatSession.getMessage()))
                .stream()
                .chatResponse()
                .map(ChatResponse::getResult);
    }
}
