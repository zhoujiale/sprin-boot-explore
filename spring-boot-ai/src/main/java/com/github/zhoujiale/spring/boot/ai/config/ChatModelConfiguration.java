package com.github.zhoujiale.spring.boot.ai.config;

import com.github.zhoujiale.spring.boot.ai.constants.PromptConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.template.st.StTemplateRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @classname: ChatModelConfiguration
 * @author: zhou
 * @description:
 * @date: 2025/9/8 13:49
 */
@Configuration
@RequiredArgsConstructor
public class ChatModelConfiguration {

    private final OllamaChatModel ollamaChatModel;

    @Bean
    public ChatClient chatClient() {
        return ChatClient.builder(ollamaChatModel)
                .defaultOptions(
                        ollamaChatModel.getDefaultOptions()
                )
                .defaultSystem(s -> s.text("你是一个" + PromptConstant.promptWrapper(PromptConstant.SCOPE) + "领域的专家，拥有丰富的知识和经验"))
                .defaultTemplateRenderer(StTemplateRenderer.builder().startDelimiterToken('<').endDelimiterToken('>').build())
                .build();
    }

    @Bean
    public MessageWindowChatMemory messageWindowChatMemory(){
        return MessageWindowChatMemory.builder()
                .maxMessages(2)
                .build();
    }


}
