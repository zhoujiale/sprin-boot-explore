package com.github.zhoujiale.spring.boot.ai.service;

import com.github.zhoujiale.spring.boot.ai.constants.PromptConstant;
import com.github.zhoujiale.spring.boot.ai.model.ChatSession;
import com.github.zhoujiale.spring.boot.ai.util.ChatMemoryUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.template.st.StTemplateRenderer;
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


    @Override
    public Flux<OllamaApi.ChatResponse> ollamaChat(ChatSession chatSession) {
        /**
         * 如果要使用原生的ollama
         * 调用必须使用ollamaApi
         **/
        return ollamaApi.streamingChat(
                OllamaApi.ChatRequest.builder(ollamaChatModel.getDefaultOptions().getModel())
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
        return chatClient.prompt()
                .advisors(new SimpleLoggerAdvisor(),
                        MessageChatMemoryAdvisor
                                .builder(ChatMemoryUtil.getChatMemory(chatSession.getSessionId()))
                                .conversationId(chatSession.getSessionId()).build())
                //用户模板增强
                .templateRenderer(StTemplateRenderer.builder().startDelimiterToken('{').endDelimiterToken('}').build())
                .user(u -> u.text("我当前身体不适，请在了解情况后提供医疗方案，下面是我的症状或我想询问的信息，{message}")
                        .param("message", chatSession.getMessage()))
                //模板替换系统提示
                .system(s -> s.param(PromptConstant.SCOPE, chatSession.getScope()))
                .stream()
                .chatResponse()
                .map(ChatResponse::getResult);
    }
}
