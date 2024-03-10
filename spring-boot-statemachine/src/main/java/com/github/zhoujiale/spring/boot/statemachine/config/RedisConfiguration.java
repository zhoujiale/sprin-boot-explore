package com.github.zhoujiale.spring.boot.statemachine.config;

import com.github.zhoujiale.spring.boot.statemachine.enums.OrderEventEnum;
import com.github.zhoujiale.spring.boot.statemachine.enums.OrderStatusEnum;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.data.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.data.redis.RedisStateMachinePersister;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;

/**
 * @classname: RedisConfiguration
 * @author: zhou
 * @description:
 * @date: 2023/5/24 15:11
 */
@Configuration
public class RedisConfiguration {

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(RedisProperties redisProperties){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisProperties.getHost());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());
        redisStandaloneConfiguration.setPassword(redisProperties.getPassword());
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public StateMachinePersist<OrderStatusEnum, OrderEventEnum,String> stateMachinePersist(LettuceConnectionFactory lettuceConnectionFactory){
        RedisStateMachineContextRepository<OrderStatusEnum, OrderEventEnum> redisStateMachineContextRepository =
                new RedisStateMachineContextRepository<>(lettuceConnectionFactory);
        return new RepositoryStateMachinePersist<>(redisStateMachineContextRepository);
    }

    @Bean
    public RedisStateMachinePersister<OrderStatusEnum, OrderEventEnum> redisStateMachinePersister(
            StateMachinePersist<OrderStatusEnum, OrderEventEnum, String> stateMachinePersist) {
        return new RedisStateMachinePersister<>(stateMachinePersist);
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(LettuceConnectionFactory connectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        this.initRedisTemplate(redisTemplate,connectionFactory);
        return redisTemplate;
    }

    private void initRedisTemplate(RedisTemplate<String,Object> redisTemplate,LettuceConnectionFactory connectionFactory){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
    }
}
