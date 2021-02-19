package com.zjl.spring_boot_rabbitmq.mq;

import com.zjl.spring_boot_rabbitmq.service.listener.FanoutListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @name: FanoutQueueConfig
 * @description:
 * @author: zhou
 * @create: 2021-02-19 11:48
 */
@Configuration
public class FanoutQueueConfig {

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("express.fanout");
    }

    @Bean
    public Queue fanoutQueueOne(){
        return new Queue("fanout1",false,false,true);
    }

    @Bean
    public Queue fanoutQueueTwo(){
        return new Queue("fanout2",false,false,true);
    }

    @Bean
    public Binding bindingOne(FanoutExchange fanoutExchange,Queue fanoutQueueOne){
        return BindingBuilder.bind(fanoutQueueOne)
                .to(fanoutExchange);
    }

    @Bean
    public Binding bindingTwo(FanoutExchange fanoutExchange,Queue fanoutQueueTwo){
        return BindingBuilder.bind(fanoutQueueTwo)
                .to(fanoutExchange);
    }

    @Bean
    public FanoutListener fanoutListener(){
        return new FanoutListener();
    }

}
