package com.github.zhoujiale.spring.boot.rabbitmq.config;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @name: RabbitMqConfiguration
 * @description: MQ配置
 * @author: zhou
 * @create: 2021-02-18 15:15
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitMqConfiguration {

    private String host;

    private Integer port;

    private String username;

    private String password;

    private String virtualHost;

    @Bean
    public CachingConnectionFactory connectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost(host);
        cachingConnectionFactory.setPort(port);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        cachingConnectionFactory.setVirtualHost(virtualHost);
        cachingConnectionFactory.setPublisherReturns(true);
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        Logger log = LoggerFactory.getLogger(RabbitTemplate.class);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //RetryTemplate retryTemplate = new RetryTemplate();
        //ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        ////首次重试间隔
        //backOffPolicy.setInitialInterval(500);
        ////乘子
        //backOffPolicy.setMultiplier(10.0);
        ////最大重试间隔
        //backOffPolicy.setMaxInterval(10000);
        //retryTemplate.setBackOffPolicy(backOffPolicy);
        //rabbitTemplate.setRetryTemplate(retryTemplate);
        //当使用returnCallback时,需要设置这个为true
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            log.error("消息:{}发送失败,响应码:{},响应信息:{},交换器:{},路由键:{}",correlationId,replyCode,replyText,exchange,routingKey);
        });
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if(ack){
                log.info("消息消费成功");
            }else {
                log.error("消息消费异常,错误信息:{}",cause);
            }
        });
        return rabbitTemplate;
    }
}
