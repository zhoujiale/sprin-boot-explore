package com.github.zhoujiale.spring.boot.ai.service;

import com.alibaba.fastjson2.JSONObject;
import com.github.zhoujiale.spring.boot.ai.constants.PromptConstant;
import com.github.zhoujiale.spring.boot.ai.model.ChatSession;
import com.github.zhoujiale.spring.boot.ai.util.ChatMemoryUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.ai.document.Document;
import org.springframework.ai.mcp.AsyncMcpToolCallbackProvider;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.template.st.StTemplateRenderer;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    private final VectorStore vectorStore;

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
                .tools(new AmapService())
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
        for (ToolCallback toolCallback : toolCallbacks) {
            System.out.println(JSONObject.toJSONString(toolCallback));
        }
        return chatClient.prompt()
                .advisors(new SimpleLoggerAdvisor())
                .toolCallbacks(toolCallbacks)
                .system(s -> s.text("""
                                你是一个<scope>领域的专家，拥有丰富的知识和经验，使用中文回答用户，不要使用英文
                                在思考过程中涉及和外部工具交互，请输出请求参数和响应结果，便于观测。请按照以下规则处理：
                                1、获取用户输入信息中起点和终点的地名,给地名增加城市的前缀<city>，通过城市信息缩小搜索范围，
                                通过外部工具将详细的结构化地址转换为经纬度坐标，坐标格式经度在前，纬度在后，经度和纬度用","分割，经纬度小数点后不得超过6位。
                                2、根据用户起点终点经纬度坐标规划
                                3、向用户返回路线规划的第一个方案，最终输出中文方案
                                """)
                        .param(PromptConstant.SCOPE, chatSession.getScope()).param("city", "杭州"))
                .user(u -> u.text("当前城市为杭州，<task>，请使用中文回答")
                        .param("task", chatSession.getMessage()))
                .stream()
                .chatResponse()
                .map(ChatResponse::getResult);
    }

    @Override
    public Flux<Generation> multimodalChat(ChatSession chatSession) {
        final String multiModalModel = "qwen2.5vl:3b";
        return chatSession.getFile()
                .content()
                .reduce(new ByteArrayOutputStream(), (outputStream, dataBuffer) -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    try {
                        outputStream.write(bytes);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to write data buffer to output stream", e);
                    }
                    DataBufferUtils.release(dataBuffer);
                    return outputStream;
                })
                .map(outputStream -> {
                    byte[] bytes = outputStream.toByteArray();
                    log.debug("文件大小: {} bytes", bytes.length);
                    return new ByteArrayResource(bytes);
                })
                .flatMapMany(byteArrayResource -> chatClient.prompt(
                                Prompt.builder()
                                        .chatOptions(
                                                ChatOptions.builder()
                                                        .model(multiModalModel)
                                                        .maxTokens(4096)
                                                        .temperature(0.7)
                                                        .build()
                                        )
                                        .messages(
                                                SystemMessage.builder()
                                                        .text("""
                                                                你是一个具有智能视觉的助手，拥有识别和分析图片、文档内容能力
                                                                """)
                                                        .build(),
                                                UserMessage.builder()
                                                        .text(chatSession.getMessage())
                                                        .media(Media.builder()
                                                                .mimeType(MimeTypeUtils.IMAGE_JPEG)
                                                                .data(byteArrayResource)
                                                                .build())
                                                        .build()
                                        )
                                        .build())
                        .advisors(new SimpleLoggerAdvisor())
                        .stream()
                        .chatResponse()
                        .map(ChatResponse::getResult));
    }

    @Override
    public Flux<Document> testAddDocuments() {
        List<Document> documents = List.of(
                new Document("Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!!", Map.of("meta1", "meta1")),
                new Document("The World is Big and Salvation Lurks Around the Corner"),
                new Document("You walk forward facing the past and you turn back toward the future.", Map.of("meta2", "meta2")));
        vectorStore.add(documents);
        return Flux.fromIterable(vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query("Spring")
                        .topK(5)
                        .build()
        ));
    }
}
