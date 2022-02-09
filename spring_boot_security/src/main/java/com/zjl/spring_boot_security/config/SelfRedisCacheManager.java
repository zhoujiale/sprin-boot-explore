package com.zjl.spring_boot_security.config;

import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

/**
 * @author zhou
 * @version 1.0
 * @className SelfRedisCacheManager
 * @description
 * @date 2021/04/20 16:39
 **/
public class SelfRedisCacheManager extends RedisCacheManager {

    public SelfRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        return super.createRedisCache(name, cacheConfig);
    }
}
