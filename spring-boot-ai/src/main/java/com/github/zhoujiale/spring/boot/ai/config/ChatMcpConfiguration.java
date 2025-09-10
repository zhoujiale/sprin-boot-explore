package com.github.zhoujiale.spring.boot.ai.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;
import org.springframework.ai.mcp.client.autoconfigure.NamedClientMcpTransport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

/**
 * @classname: ChatMcpConfiguration
 * @author: zhou
 * @description:
 * @date: 2025/9/10 16:10
 */
@Configuration
public class ChatMcpConfiguration {


    @Bean
    public List<NamedClientMcpTransport> mcpClientTransport() {
        return List.of(
                new NamedClientMcpTransport("amap",
                        HttpClientSseClientTransport.builder("https://mcp.amap.com")
                                .sseEndpoint("/sse?key=########")
                                .objectMapper(new ObjectMapper())
                                .customizeClient(clientBuilder -> clientBuilder.connectTimeout(Duration.ofSeconds(60)))
                                .build())
        );
    }
}
