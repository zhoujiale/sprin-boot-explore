package com.github.zhoujiale.spring.boot.rabbitmq.service.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @name: SimpleListener2
 * @description:
 * @author: zhou
 * @create: 2021-02-18 21:48
 */
@Slf4j
public class SimpleListener2 {

    @RabbitListener(queues = "simple")
    public void receive(String msg){
        log.info("simple2 receive msg:[{}]",msg);
    }
}
