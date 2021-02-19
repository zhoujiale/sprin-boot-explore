package com.zjl.spring_boot_rabbitmq.mq;

import com.zjl.spring_boot_rabbitmq.service.listener.TopicListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @name: TopicQueueConfig
 * @description:
 * @author: zhou
 * @create: 2021-02-19 13:24
 */
@Configuration
public class TopicQueueConfig {

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("express.topic");
    }

    @Bean
    public Queue topicQueueOne(){
        return new Queue("food",false,false,true);
    }

    @Bean
    public Queue topicQueueTwo(){
        return new Queue("appliance",false,false,true);
    }

    @Bean
    public Queue topicQueueThree(){
        return new Queue("fruit",false,false,true);
    }

    @Bean
    public Binding topicBindingOne(TopicExchange topicExchange,Queue topicQueueOne){
        return BindingBuilder.bind(topicQueueOne)
                .to(topicExchange)
                .with("food.delicatessen");
    }

    @Bean
    public Binding topicBindingTwo(TopicExchange topicExchange,Queue topicQueueTwo){
        return BindingBuilder.bind(topicQueueTwo)
                .to(topicExchange)
                .with("appliance.*.bigSize");
    }

    @Bean
    public Binding topicBindingThree(TopicExchange topicExchange,Queue topicQueueThree){
        return BindingBuilder.bind(topicQueueThree)
                .to(topicExchange)
                .with("fruit.#");
    }

    @Bean
    public TopicListener topicListener(){
        return new TopicListener();
    }
}
