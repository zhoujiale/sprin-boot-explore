package com.github.zhoujiale.spring.boot.ai.util;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @classname: ChatMemoryUtil
 * @author: zhou
 * @description: 简易会话存储
 * @date: 2025/9/8 17:04
 */
public class ChatMemoryUtil {

    private static final ConcurrentHashMap<String, ChatMemory> CHAT_MEMORY_MAP = new ConcurrentHashMap<>();


    public static void addChatMemory(String sessionId, ChatMemory chatMemory) {
        CHAT_MEMORY_MAP.put(sessionId, chatMemory);
    }

    public static ChatMemory getChatMemory(String sessionId) {
        ChatMemory chatMemory = CHAT_MEMORY_MAP.get(sessionId);
        if (null == chatMemory){
            chatMemory = MessageWindowChatMemory.builder()
                    .maxMessages(5)
                    .build();
            addChatMemory(sessionId,chatMemory);
        }
        return chatMemory;
    }
}
