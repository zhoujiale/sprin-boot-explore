package com.zjl.spring_boot_redis.config;

import com.zjl.spring_boot_redis.util.RedisUtil;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhou
 * @version 1.0
 * @className RedisConfiguration
 * @description
 * @date 2021/04/20 17:15
 **/
@EnableCaching
@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {


    @Bean(name = "selfKeyGenerator")
    public KeyGenerator sassKeyGenerator(){
        final String prefix = "self";
        final String sp = ":";
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(prefix).append(sp);
            sb.append(target.getClass().getSimpleName())
                    .append(sp);
            sb.append(method.getName());
            for (Object param:params){
                sb.append(sp);
                sb.append(param);
            }
            return sb.toString();
        };
    }

    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory connectionFactory){
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair
                .fromSerializer(new GenericJackson2JsonRedisSerializer());
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeValuesWith(pair)
                .entryTtl(Duration.ofMinutes(10));
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory))
                .cacheDefaults(defaultCacheConfig).build();

    }


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

    @Bean
    public RedisUtil redisUtil(RedisTemplate<String,Object> redisTemplate){
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }

    /**
     * @description redis键事件监听
     * @author zhou
     * @create 2021/4/20 20:48
     * @param 
     * @return org.springframework.data.redis.listener.RedisMessageListenerContainer
     **/
    @Bean
    public RedisMessageListenerContainer container(LettuceConnectionFactory lettuceConnectionFactory, RedisKeyListener redisKeyListener){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(lettuceConnectionFactory);
        List<Topic> topics = new ArrayList<>();
        topics.add(new PatternTopic("__keyspace@0__:"));
        container.addMessageListener(redisKeyListener,topics);
        return container;
    }
}
