package com.zjl.spring_boot_rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author zhou
 * @className Listener
 * @descrption TODO
 * @date 2022/7/7 17:37
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "spring-topic",selectorExpression = "one || two",consumerGroup = "self-consumer-group")
public class Listener implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        log.info(s);
    }
}
