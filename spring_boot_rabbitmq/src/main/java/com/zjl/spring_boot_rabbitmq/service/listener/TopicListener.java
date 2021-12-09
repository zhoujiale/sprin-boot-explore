package com.zjl.spring_boot_rabbitmq.service.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;

/**
 * @name: TopicListener
 * @description:
 * @author: zhou
 * @create: 2021-02-19 13:33
 */
@Slf4j
public class TopicListener {


    @RabbitListener(queues = "#{topicQueueOne.name}")
    public void foodListener(String message) {
        log.info("receive delicatessen food:[{}]", message);
    }

    @RabbitListener(queues = "#{topicQueueTwo.name}")
    public void applianceListener(String message) {
        log.info("receive big appliance:[{}]", message);
    }

    @RabbitListener(queues = "#{topicQueueThree.name}")
    public void fruitListener(String message) {
        log.info("receive any fruit:[{}]", message);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "web_queue", durable = "false", autoDelete = "true", exclusive = "false"),
            exchange = @Exchange(value = "web", type = ExchangeTypes.TOPIC),
            key = {"web.info"}
    ))
    public void webListener(String message) {
        log.info("web info:[{}]", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "dlx_queue", durable = "false", autoDelete = "true", exclusive = "false"),
            exchange = @Exchange(value = "dlx_exchange", type = ExchangeTypes.TOPIC),
            key = {"web.expire"}
    ))
    public void delayListener(String message) {
        log.info("delay info:[{}]", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "confirm_queue", durable = "false", autoDelete = "true", exclusive = "false"),
            exchange = @Exchange(value = "confirm_exchange", type = ExchangeTypes.TOPIC),
            key = {"confirm.manual"}
    ))
    public void confirmListener(Message message, Channel channel) {
        try {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("confirm info:[{}]", message);
    }
}
