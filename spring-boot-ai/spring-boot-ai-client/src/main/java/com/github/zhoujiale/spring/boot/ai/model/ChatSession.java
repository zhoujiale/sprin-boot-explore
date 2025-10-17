package com.github.zhoujiale.spring.boot.ai.model;

import lombok.Data;
import org.springframework.http.codec.multipart.FilePart;

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

    /**
     * 文件
     **/
    private FilePart file;

}
