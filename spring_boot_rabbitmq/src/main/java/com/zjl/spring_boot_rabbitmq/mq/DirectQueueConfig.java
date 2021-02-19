package com.zjl.spring_boot_rabbitmq.mq;

import com.zjl.spring_boot_rabbitmq.service.listener.DirectListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @name: DirectQueueConfig
 * @description:
 * @author: zhou
 * @create: 2021-02-19 12:58
 */
@Configuration
public class DirectQueueConfig {

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("express.item");
    }

    @Bean
    public Queue directQueueOne(){
        return new Queue("clothes",false,false,true);
    }

    @Bean
    public Queue directQueueTwo(){
        return new Queue("cup",false,false,true);
    }

    @Bean
    public Binding directBingOne(DirectExchange directExchange,Queue directQueueOne){
        return BindingBuilder.bind(directQueueOne)
                .to(directExchange)
                .with("normal");
    }

    @Bean
    public Binding directBingTwo(DirectExchange directExchange,Queue directQueueTwo){
        return BindingBuilder.bind(directQueueTwo)
                .to(directExchange)
                .with("fragile");
    }

    @Bean
    public DirectListener directListener(){
        return new DirectListener();
    }
}
