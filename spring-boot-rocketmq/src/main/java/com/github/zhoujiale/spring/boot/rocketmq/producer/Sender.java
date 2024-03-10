package com.github.zhoujiale.spring.boot.rocketmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhou
 * @className Sender
 * @descrption 发送者
 * @date 2022/7/7 17:20
 */
@Slf4j
@RestController
public class Sender {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    @PostMapping("/send")
    public void sendMsg(@RequestParam(value = "msg") String msg,@RequestParam(value = "topic")String topic,
                        @RequestParam(value = "tag")String tag){
        //主题
        Message<String> message = MessageBuilder.withPayload(msg).build();
        rocketMQTemplate.asyncSend(topic + ":" + tag, message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("send success:{}",msg);
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("send error:{}",throwable.getMessage());
            }
        });
    }
}
