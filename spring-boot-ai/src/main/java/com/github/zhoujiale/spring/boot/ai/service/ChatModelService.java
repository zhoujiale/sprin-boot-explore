package com.github.zhoujiale.spring.boot.ai.service;

import com.github.zhoujiale.spring.boot.ai.model.ChatSession;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.ollama.api.OllamaApi;
import reactor.core.publisher.Flux;

/**
 * @classname: ChatModelService
 * @author: zhou
 * @description:
 * @date: 2025/9/8 13:43
 */
public interface ChatModelService {
    Flux<Generation> chat(ChatSession chatSession);

    Flux<OllamaApi.ChatResponse> ollamaChat(ChatSession chatSession);

    Flux<Generation> methodChat(ChatSession chatSession);

    Flux<Generation> functionChat(ChatSession chatSession);

    Flux<Generation> mcpChat(ChatSession chatSession);

    Flux<Generation> multimodalChat(ChatSession chatSession);
}
