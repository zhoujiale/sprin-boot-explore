package com.github.zhoujiale.spring.boot.rabbitmq.mq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhou
 * @version 1.0
 * @className DelayQueueConfig
 * @description 延时队列
 * @date 2021/07/12 12:07
 **/
@Configuration
public class DelayQueueConfig {

    private static final String DELAY_TYPE = "x-delayed-message";

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

    /**
     * @param
     * @return org.springframework.amqp.core.CustomExchange
     * @description 延时交换器
     * @date 2022/4/6 20:42
     * @author zhou
     */
    @Bean
    public CustomExchange customDelayExchange() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "topic");
        return new CustomExchange("plugin_delay_exchange", "x-delayed-message", false, true, args);
    }

    @Bean
    public Queue pluginDelayQueue(){
        return new Queue("plugin_delay_queue");
    }

    @Bean
    public Binding delayBinding(CustomExchange customDelayExchange,Queue pluginDelayQueue){
        return BindingBuilder
                .bind(pluginDelayQueue)
                .to(customDelayExchange)
                .with("plugin.delay").noargs();
    }
}
