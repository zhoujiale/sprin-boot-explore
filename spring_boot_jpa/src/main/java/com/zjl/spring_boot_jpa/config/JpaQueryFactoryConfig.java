package com.zjl.spring_boot_jpa.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/**
 * @author zhou
 * @version 1.0
 * @className JpaQueryFactoryConfig
 * @description
 * @date 2021/02/20 14:05
 **/
@Configuration
public class JpaQueryFactoryConfig {

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager){
        return new JPAQueryFactory(entityManager);
    }
}
