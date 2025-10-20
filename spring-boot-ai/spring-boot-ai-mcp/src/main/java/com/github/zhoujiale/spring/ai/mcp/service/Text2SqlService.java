package com.github.zhoujiale.spring.ai.mcp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @classname: Text2SqlServiceImpl
 * @author: zhou
 * @description:
 * @date: 2025/10/20 12:58
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class Text2SqlService {

    @Tool(name = "generate_sql",description = "将自然语言问题转换成SQL查询语句")
    public Mono<String> generateSql(String text) {
        return Mono.just("select * from user where name = 'zhou'");
    }
}
