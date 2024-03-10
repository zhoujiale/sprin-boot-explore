package com.github.zhoujiale.spring.boot.rabbitmq.mq;

import com.github.zhoujiale.spring.boot.rabbitmq.service.listener.SimpleListener;
import com.github.zhoujiale.spring.boot.rabbitmq.service.listener.SimpleListener2;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @name: SimpleQueueConfig
 * @description:
 * @author: zhou
 * @create: 2021-02-18 19:12
 */
@Configuration
public class SimpleQueueConfig {

    public static final String QUEUE_NAME = "simple";

    @Bean
    public Queue simple(){
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public SimpleListener simpleListener(){
        return new SimpleListener();
    }

    @Bean
    public SimpleListener2 simpleListener2(){
        return new SimpleListener2();
    }
}
