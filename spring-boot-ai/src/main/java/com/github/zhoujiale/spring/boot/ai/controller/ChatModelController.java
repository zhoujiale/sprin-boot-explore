package com.github.zhoujiale.spring.boot.ai.controller;

import com.alibaba.fastjson2.JSONObject;
import com.github.zhoujiale.spring.boot.ai.model.ChatSession;
import com.github.zhoujiale.spring.boot.ai.service.ChatModelService;
import io.modelcontextprotocol.client.McpAsyncClient;
import io.modelcontextprotocol.spec.McpSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.mcp.AsyncMcpToolCallbackProvider;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chatModel")
@RequiredArgsConstructor
public class ChatModelController {

    private final OllamaChatModel ollamaChatModel;

    private final ChatModelService chatModelService;

    private final List<McpAsyncClient> mcpAsyncClients;

    private final AsyncMcpToolCallbackProvider asyncMcpToolCallbackProvider;


    @PostMapping(value = "/ollamaChat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<OllamaApi.ChatResponse> ollamaChat(@RequestBody ChatSession chatSession) {
        return chatModelService.ollamaChat(chatSession);
    }

    @PostMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Generation> chat(@RequestBody ChatSession chatSession) {
        return chatModelService.chat(chatSession);
    }

    @PostMapping(value = "/methodChat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Generation> methodChat(@RequestBody ChatSession chatSession) {
        return chatModelService.methodChat(chatSession);
    }

    @PostMapping(value = "/functionChat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Generation> functionChat(@RequestBody ChatSession chatSession) {
        return chatModelService.functionChat(chatSession);
    }

    @PostMapping(value = "/mcpChat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Generation> mcpChat(@RequestBody ChatSession chatSession) {
        return chatModelService.mcpChat(chatSession);
    }

    @PostMapping(value = "/multimodalChat", produces = MediaType.TEXT_EVENT_STREAM_VALUE,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Flux<Generation> multimodalChat(ChatSession chatSession) {
        return chatModelService.multimodalChat(chatSession);
    }

    @PostMapping(value = "/test", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<McpSchema.CallToolResult> test() {
        McpAsyncClient mcpAsyncClient = mcpAsyncClients.get(0);
        for (ToolCallback toolCallback : asyncMcpToolCallbackProvider.getToolCallbacks()) {
            System.out.println(JSONObject.toJSONString(toolCallback));
        }
        // 黄龙体育中心 120.133794,30.266755
        // 远洋乐堤港 120.142757,30.305211
        return mcpAsyncClient
                .listTools()
                .flatMap(tools -> mcpAsyncClient
                        .callTool(new McpSchema.CallToolRequest(
                                "maps_direction_transit_integrated",
                                Map.of("origin", "120.133794,30.266755", "destination", "120.142757,30.305211",
                                        "city", "杭州", "cityd", "杭州")
                        )));
    }
}
