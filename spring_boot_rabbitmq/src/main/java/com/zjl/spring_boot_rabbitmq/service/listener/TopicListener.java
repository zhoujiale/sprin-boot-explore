package com.zjl.spring_boot_rabbitmq.service.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @name: TopicListener
 * @description:
 * @author: zhou
 * @create: 2021-02-19 13:33
 */
@Slf4j
public class TopicListener {


    @RabbitListener(queues = "#{topicQueueOne.name}")
    public void foodListener(String message){
        log.info("receive delicatessen food:[{}]",message);
    }

    @RabbitListener(queues = "#{topicQueueTwo.name}")
    public void applianceListener(String message){
        log.info("receive big appliance:[{}]",message);
    }

    @RabbitListener(queues = "#{topicQueueThree.name}")
    public void fruitListener(String message){
        log.info("receive any fruit:[{}]",message);
    }
}
