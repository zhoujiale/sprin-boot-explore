package com.zjl.spring_boot_rocketmq.producer;

import io.netty.channel.DefaultChannelId;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author zhou
 * @version 1.0
 * @className SyncProducer
 * @description
 * @date 2022/02/18 17:35
 **/
public class SyncProducer {

    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
                DefaultMQProducer("please_rename_unique_group_name");
        // Specify name server addresses.
        producer.setNamesrvAddr("121.199.0.246:9876");
        DefaultChannelId.newInstance();
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 1; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("forum-topic" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            System.out.println(System.currentTimeMillis());
            SendResult sendResult = producer.send(msg);
            System.out.println(System.currentTimeMillis());
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        //producer.shutdown();
    }
}
