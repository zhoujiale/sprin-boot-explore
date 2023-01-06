package com.zjl.spring_boot_redis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author zhou
 * @version 1.0
 * @className RedisKeyListener
 * @description
 * @date 2021/04/20 17:36
 **/
@Slf4j
@Component
public class RedisKeyListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] bytes) {
        log.info(message.toString());
    }
}
