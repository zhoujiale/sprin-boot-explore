package com.zjl.spring_boot_rocketmq.consumer;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @classname: PullConsumer
 * @author: zhou
 * @description:
 * @date: 2023/5/4 15:04
 */
public class PullConsumer {

    public static void main(String[] args) throws MQClientException {
        DefaultLitePullConsumer defaultLitePullConsumer = new DefaultLitePullConsumer("self-consumer-group");

        defaultLitePullConsumer.setNamesrvAddr("60.167.177.183:9876");
        defaultLitePullConsumer.subscribe("TopicTest","*");
        defaultLitePullConsumer.setAutoCommit(false);
        defaultLitePullConsumer.start();
        while (true){
            List<MessageExt> messageExts = defaultLitePullConsumer.poll(1000);
            if (CollectionUtils.isNotEmpty(messageExts)){
                for (MessageExt messageExt : messageExts) {
                    System.out.println(messageExt.toString());
//                    int a = 1/0;
                    defaultLitePullConsumer.commitSync();
                }
            }
        }
    }
}
