package com.github.zhoujiale.spring.boot.ai.controller;

import com.github.zhoujiale.spring.boot.ai.model.ChatSession;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/chatModel")
@RequiredArgsConstructor
public class ChatModelController {

    private final OllamaChatModel ollamaChatModel;

    @PostMapping(value = "/chat")
    public Mono<String> chat(@RequestBody ChatSession chatSession){
        return Mono.fromCallable(() -> ollamaChatModel.call(chatSession.getMessage()))
                .publishOn(Schedulers.boundedElastic());
    }
}
