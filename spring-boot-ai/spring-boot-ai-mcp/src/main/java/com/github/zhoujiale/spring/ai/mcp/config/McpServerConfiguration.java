package com.github.zhoujiale.spring.ai.mcp.config;

import com.github.zhoujiale.spring.ai.mcp.service.Text2SqlService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @classname: McpServerConfiguration
 * @author: zhou
 * @description:
 * @date: 2025/10/20 13:14
 */
@Configuration
public class McpServerConfiguration {


    @Bean
    public ToolCallbackProvider toolRegister(Text2SqlService text2SqlService){
        return MethodToolCallbackProvider.builder()
                .toolObjects(text2SqlService)
                .build();
    }
}
