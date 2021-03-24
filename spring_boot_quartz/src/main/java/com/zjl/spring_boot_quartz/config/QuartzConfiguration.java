package com.zjl.spring_boot_quartz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author zhou
 * @version 1.0
 * @className QuartzConfiguration
 * @description
 * @date 2021/02/20 18:57
 **/
@Configuration
public class QuartzConfiguration {

    private static final String SCHEDULER_NAME = "Self_Scheduler";

    private static final String SCHEDULER_CONTEXT_KEY = "applicationContextKey";

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource,Properties quartzProperties){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setSchedulerName(SCHEDULER_NAME);
        schedulerFactoryBean.setQuartzProperties(quartzProperties);
        schedulerFactoryBean.setApplicationContextSchedulerContextKey(SCHEDULER_CONTEXT_KEY);
        return schedulerFactoryBean;
    }

    @Bean
    public Properties quartzProperties(){
        Properties properties = new Properties();
        //定时器实例名称
        properties.put("org.quartz.scheduler.instanceName","SelfQuartzScheduler");
        properties.put("org.quartz.scheduler.instanceId","AUTO");
        //线程池
        properties.put("org.quartz.threadPool.class","org.quartz.simpl.SimpleThreadPool");
        properties.put("org.quartz.threadPool.threadCount","20");
        properties.put("org.quartz.threadPool.threadPriority","5");
        //jobStore

        return properties;
    }
}
