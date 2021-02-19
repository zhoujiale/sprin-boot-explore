package com.zjl.spring_boot_rabbitmq.service.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @name: DirectListener
 * @description:
 * @author: zhou
 * @create: 2021-02-19 13:05
 */
@Slf4j
public class DirectListener {

    @RabbitListener(queues = "#{directQueueOne.name}")
    public void normalListener(String message){
        log.info("normal express:[{}]",message);
    }

    @RabbitListener(queues = "#{directQueueTwo.name}")
    public void fragileListener(String message){
        log.info("fragile express:[{}]",message);
    }
}
