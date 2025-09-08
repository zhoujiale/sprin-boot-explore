package com.github.zhoujiale.spring.boot.ai.controller;

import com.github.zhoujiale.spring.boot.ai.model.ChatSession;
import com.github.zhoujiale.spring.boot.ai.service.ChatModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chatModel")
@RequiredArgsConstructor
public class ChatModelController {

    private final OllamaChatModel ollamaChatModel;

    private final ChatModelService chatModelService;

    @PostMapping(value = "/ollamaChat",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<OllamaApi.ChatResponse> ollamaChat(@RequestBody ChatSession chatSession){
        return chatModelService.ollamaChat(chatSession);
    }

    @PostMapping(value = "/chat",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Generation> chat(@RequestBody ChatSession chatSession){
        return chatModelService.chat(chatSession);
    }
}
