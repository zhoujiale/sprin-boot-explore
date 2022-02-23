package com.zjl.spring_boot_rocketmq.producer;

import io.netty.channel.DefaultChannelId;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author zhou
 * @version 1.0
 * @className BroadcastProducer
 * @description
 * @date 2022/02/21 11:45
 **/
public class BroadcastProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        DefaultChannelId.newInstance();
        producer.start();

        for (int i = 0; i < 100; i++){
            Message msg = new Message("Broadcast",
                    "TagA",
                    "OrderID188",
                    "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //producer.shutdown();
    }
}
