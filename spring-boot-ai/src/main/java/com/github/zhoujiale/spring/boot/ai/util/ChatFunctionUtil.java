package com.github.zhoujiale.spring.boot.ai.util;

import com.github.zhoujiale.spring.boot.ai.service.WeatherRequest;
import com.github.zhoujiale.spring.boot.ai.service.WeatherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

/**
 * @classname: ChatFunctionUtil
 * @author: zhou
 * @description:
 * @date: 2025/9/10 13:45
 */
@Configuration(proxyBeanMethods = false)
public class ChatFunctionUtil {

    @Bean("weatherFunction")
    @Description("天气查询")
    public Function<WeatherRequest, String> weatherFunction() {
        return new WeatherService();
    }
}
