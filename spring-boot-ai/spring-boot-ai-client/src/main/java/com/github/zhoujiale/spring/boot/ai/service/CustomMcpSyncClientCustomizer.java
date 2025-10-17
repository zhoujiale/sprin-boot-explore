package com.github.zhoujiale.spring.boot.ai.service;

import io.modelcontextprotocol.client.McpClient;
import org.springframework.ai.mcp.customizer.McpAsyncClientCustomizer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @classname: CustomMcpSyncClientCustomizer
 * @author: zhou
 * @description:
 * @date: 2025/9/10 17:54
 */
@Component
public class CustomMcpSyncClientCustomizer implements McpAsyncClientCustomizer {

    @Override
    public void customize(String name, McpClient.AsyncSpec spec) {
        spec.loggingConsumer(loggingMessageNotification -> {
            System.out.println("MCP Logging: " +loggingMessageNotification.data());
            return Mono.empty();
        });
    }
}
