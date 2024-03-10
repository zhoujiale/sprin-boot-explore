package com.github.zhoujiale.spring.boot.rabbitmq.service.impl;

import com.github.zhoujiale.spring.boot.rabbitmq.service.ProducerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProducerServiceImplTest {

    @Autowired
    private ProducerService producerService;

    @Test
    void sendSimpleMessage() {
        String msg = "京东快递";
        producerService.sendSimpleMessage(msg);
    }

    @Test
    void workSend(){
        int total = 10;
        for(int i = 0;i< total;i++){
            String msg = "邮件-"+i;
            producerService.sendSimpleMessage(msg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void fanoutSendMessage() {
        String message = "开工";
        producerService.fanoutSendMessage(message);
    }

    @Test
    void directSendMessage() {
        String normalProduct = "牛仔裤";
        String fragileProduct = "玻璃杯";
        producerService.directSendMessage(normalProduct,"normal");
        producerService.directSendMessage(fragileProduct,"fragile");
    }

    @Test
    void topicSendMessage() {
        String food1 = "熟牛肉";
        String food2 = "土鸡";
        producerService.topicSendMessage(food1,"food.delicatessen");
        producerService.topicSendMessage(food2,"food.other");

        String appliance1 = "冰箱";
        String appliance2 = "彩电";
        String appliance3 = "电热水壶";
        producerService.topicSendMessage(appliance1,"appliance.refrigerator.bigSize");
        producerService.topicSendMessage(appliance2,"appliance.tv.bigSize");
        producerService.topicSendMessage(appliance3,"appliance.kettle.smallSize");

        String fruit1 = "猕猴桃";
        String fruit2 = "柚子";
        String fruit3 = "红心柚";
        producerService.topicSendMessage(fruit1,"fruit.kiwi");
        producerService.topicSendMessage(fruit2,"fruit.pomelo.yellow");
        producerService.topicSendMessage(fruit3,"fruit.pomelo.red.big");

    }
}