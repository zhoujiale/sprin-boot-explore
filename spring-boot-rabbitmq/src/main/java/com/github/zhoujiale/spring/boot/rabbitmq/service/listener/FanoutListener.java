package com.github.zhoujiale.spring.boot.rabbitmq.service.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.time.LocalDateTime;

/**
 * @name: FanoutListener
 * @description:
 * @author: zhou
 * @create: 2021-02-19 12:18
 */
@Slf4j
public class FanoutListener {

    @RabbitListener(queues = "#{fanoutQueueOne.name}")
    public void fanoutListenerOne(String msg){
        log.info("fanoutQueue1 receive:[{}],time:[{}]",msg, LocalDateTime.now());
    }

    @RabbitListener(queues = "#{fanoutQueueTwo.name}")
    public void fanoutListenerTwo(String msg){
        log.info("fanoutQueue2 receive:[{}],time:[{}]",msg,LocalDateTime.now());
    }
}
