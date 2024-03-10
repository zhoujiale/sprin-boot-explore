package com.github.zhoujiale.spring.boot.quartz.service;

/**
 * @author zhou
 * @className SelfJobService
 * @date 2021/04/19 17:10
 * @description
 **/
public interface SelfJobService {

    void minuteJob(String params);
}
