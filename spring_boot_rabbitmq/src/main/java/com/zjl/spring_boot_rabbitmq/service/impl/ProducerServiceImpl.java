package com.zjl.spring_boot_rabbitmq.service.impl;

import com.zjl.spring_boot_rabbitmq.mq.SimpleQueueConfig;
import com.zjl.spring_boot_rabbitmq.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @name: ProducerServiceImpl
 * @description:
 * @author: zhou
 * @create: 2021-02-18 18:28
 */
@Slf4j
@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private FanoutExchange fanoutExchange;
    @Autowired
    private DirectExchange directExchange;
    @Autowired
    private TopicExchange topicExchange;


    @Override
    public void sendSimpleMessage(String message) {
        LocalDateTime now = LocalDateTime.now();
        log.info("producer simple msg:[{}]", now.toString());
        rabbitTemplate.convertAndSend(SimpleQueueConfig.QUEUE_NAME, message);
        log.info("send simple msg:[{}]", message);
    }

    @Override
    public void fanoutSendMessage(String message) {
        LocalDateTime now = LocalDateTime.now();
        log.info("producer message time:[{}]", now.toString());
        rabbitTemplate.convertAndSend(fanoutExchange.getName(), StringUtils.EMPTY, message);
        log.info("producer fanout message:[{}]", message);
    }

    @Override
    public void directSendMessage(String message, String routingKey) {
        LocalDateTime now = LocalDateTime.now();
        log.info("producer direct time:[{}]", now.toString());
        rabbitTemplate.convertAndSend(directExchange.getName(), routingKey, message);
        log.info("producer direct message:[{}]", message);
    }

    @Override
    public void topicSendMessage(String message, String routingKey) {
        LocalDateTime now = LocalDateTime.now();
        log.info("producer topic time:[{}]", now.toString());
        rabbitTemplate.convertAndSend(topicExchange.getName(), routingKey, message);
        log.info("producer topic message:[{}]", message);
    }

    @Override
    public void delayInfo(String message) {
        LocalDateTime now = LocalDateTime.now();
        rabbitTemplate.convertAndSend("dead_exchange", "dlx", message,
                m -> {
                    m.getMessageProperties().setExpiration(String.valueOf(5000));
                    return m;
                }
        );
        log.info("发送延时时间:[{}]", now.toString());
    }

    @Override
    public void confirmInfo(String message) {
        LocalDateTime now = LocalDateTime.now();
        rabbitTemplate.convertAndSend("confirm_exchange","confirm.reject",message);
        log.info("发送时间:[{}]",now.toString());
    }
}
