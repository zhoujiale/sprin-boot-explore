package com.github.zhoujiale.spring.boot.security.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author zhou
 * @version 1.0
 * @className RedisConfiguration
 * @description
 * @date 2021/04/25 16:47
 **/
@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {

    /**
     * @description 将redisTemplate注入容器
     * @author zhou
     * @create 2021/4/20 17:30
     * @param
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.Object>
     **/
    @Bean
    public RedisTemplate<String,Object> redisTemplate(LettuceConnectionFactory connectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        this.initRedisTemplate(redisTemplate,connectionFactory);
        return redisTemplate;
    }

    /**
     * @description 初始化redis序列化方式,不开启事务
     * @author zhou
     * @create 2021/4/20 17:29
     * @param
     * @return void
     **/
    private void initRedisTemplate(RedisTemplate<String,Object> redisTemplate,LettuceConnectionFactory connectionFactory){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 开启事务
        //redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(connectionFactory);
    }
}
