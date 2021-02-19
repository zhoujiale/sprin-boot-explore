package com.zjl.spring_boot_rabbitmq.service.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @name: SimpleListener
 * @description:
 * @author: zhou
 * @create: 2021-02-18 18:48
 */
@Slf4j
@RabbitListener(queues = "simple")
public class SimpleListener {

    @RabbitHandler
    public void receive(String msg){
        log.info("simple receive msg:[{}]",msg);
    }
}
