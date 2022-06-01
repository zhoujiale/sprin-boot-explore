package com.zjl.spring_boot_web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author zhou
 * @className ThymeleafConfiguration
 * @descrption thymeleaf配置
 * @date 2022/5/31 19:03
 */
@Configuration
public class ThymeleafConfiguration {

    @Bean
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("lessonList");
        return resourceBundleMessageSource;
    }
}
