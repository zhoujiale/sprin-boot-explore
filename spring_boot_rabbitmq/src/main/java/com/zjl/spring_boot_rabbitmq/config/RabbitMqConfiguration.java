package com.zjl.spring_boot_rabbitmq.config;

import lombok.Data;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
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
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());
        return rabbitTemplate;
    }
}
