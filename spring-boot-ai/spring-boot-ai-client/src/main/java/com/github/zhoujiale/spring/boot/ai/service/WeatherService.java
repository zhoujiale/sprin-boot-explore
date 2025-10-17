package com.github.zhoujiale.spring.boot.ai.service;

import lombok.Data;

import java.util.function.Function;

/**
 * @classname: WeatherService
 * @author: zhou
 * @description:
 * @date: 2025/9/10 13:50
 */
@Data
public class WeatherService implements Function<WeatherRequest,String> {

    @Override
    public String apply(WeatherRequest weatherRequest) {
        return weatherRequest.city() + "天气有暴雨还是别出门了。。。";
    }
}
