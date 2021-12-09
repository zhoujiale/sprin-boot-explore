package com.zjl.spring_boot_rabbitmq.service;

/**
 * @Auther: zhou
 * @Date: 2021-02-18 18:24
 * @Description:
 */
public interface ProducerService {

    /**
     * @description 发送消息
     * @author zhou
     * @created  2021/2/18 18:41
     * @param message
     * @return void
     **/
    void sendSimpleMessage(String message);
    
    /** 
     * @description 广播消息 
     * @author zhou       
     * @created  2021/2/19 12:58
     * @param 
     * @return void
     **/
    void fanoutSendMessage(String message);

    /**
     * @description 通过路由键传递消息
     * @author zhou
     * @created  2021/2/19 13:08
     * @param message 消息
     * @param routingKey 路由键
     * @return void
     **/
    void directSendMessage(String message,String routingKey);

    /**
     * @description 主题交换机分发消息
     * @author zhou
     * @created  2021/2/19 13:39
     * @param message 消息
     * @param routingKey 路由键
     * @return void
     **/
    void topicSendMessage(String message,String routingKey);
    
    /**
     * @description 发送一条延时消息
     * @author zhou
     * @create 2021/7/12 13:27
     * @param 
     * @return void
     **/
    void delayInfo(String message);
    
    /**
     * @description 手动确认消息
     * @author zhou
     * @create 2021/7/13 14:16 
     * @param 
     * @return void
     **/
    void confirmInfo(String message);
}
