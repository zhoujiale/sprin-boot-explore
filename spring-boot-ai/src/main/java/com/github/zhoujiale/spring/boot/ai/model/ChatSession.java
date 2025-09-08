package com.github.zhoujiale.spring.boot.ai.model;

import lombok.Data;

@Data
public class ChatSession {

    /**
     * 消息
     **/
    private String message;

    /**
     * 领域
     **/
    private String scope;

    /**
     * 会话id
     **/
    private String sessionId = "default";
}
