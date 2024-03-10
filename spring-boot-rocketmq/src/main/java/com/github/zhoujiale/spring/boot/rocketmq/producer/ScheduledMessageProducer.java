package com.github.zhoujiale.spring.boot.rocketmq.producer;

import io.netty.channel.DefaultChannelId;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @author zhou
 * @version 1.0
 * @className ScheduledMessageProducer
 * @description
 * @date 2022/02/21 11:58
 **/
public class ScheduledMessageProducer {

    public static void main(String[] args) throws Exception {
        // Instantiate a producer to send scheduled messages
        DefaultMQProducer producer = new DefaultMQProducer("ExampleProducerGroup");
        // Launch producer
        DefaultChannelId.newInstance();
        producer.start();
        int totalMessagesToSend = 1;
        for (int i = 0; i < totalMessagesToSend; i++) {
            Message message = new Message("Scheduled", ("Hello scheduled message " + System.currentTimeMillis()).getBytes());
            // This message will be delivered to consumer 10 seconds later.

            message.setDelayTimeLevel(3);
            // Send the message
            SendResult send = producer.send(message);
            System.out.println(send.getSendStatus()+"-"+System.currentTimeMillis());
        }

        // Shutdown producer after use.
        //producer.shutdown();
    }
}
