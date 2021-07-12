package com.zjl.spring_boot_rabbitmq.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author zhou
 * @version 1.0
 * @className DelayQueueConfig
 * @description 延时队列
 * @date 2021/07/12 12:07
 **/
@Configuration
public class DelayQueueConfig {

    /**
     * @param
     * @return org.springframework.amqp.core.TopicExchange
     * @description 定义一个死信交换器
     * @author zhou
     * @create 2021/7/12 12:27
     **/
    @Bean
    public TopicExchange deadExchange() {
        return new TopicExchange("dead_exchange", false, false);
    }

    /**
     * @param
     * @return org.springframework.amqp.core.Queue
     * @description 死信队列
     * @author zhou
     * @create 2021/7/12 12:31
     **/
    @Bean
    public Queue deadQueue() {
        HashMap<String, Object> args = new HashMap<>();
        //要发送的交换器
        args.put("x-dead-letter-exchange", "dlx_exchange");
        //路由键
        args.put("x-dead-letter-routing-key", "web.expire");
        return new Queue("dead_queue", false, false, false, args);
    }

    /**
     * @param
     * @return org.springframework.amqp.core.Binding
     * @description 建立绑定关系
     * @author zhou
     * @create 2021/7/12 12:38
     **/
    @Bean
    public Binding deadBinding(TopicExchange deadExchange, Queue deadQueue) {
        return BindingBuilder
                .bind(deadQueue)
                .to(deadExchange)
                .with("dlx");
    }
}
