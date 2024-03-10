package com.github.zhoujiale.spring.boot.quartz.config;

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
    
    /**
     * @description 调度器工厂类
     * @author zhou
     * @create 2021/3/24 12:27 
     * @param 
     * @return org.springframework.scheduling.quartz.SchedulerFactoryBean
     **/
    @Bean("schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource, Properties quartzProperties){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setSchedulerName(SCHEDULER_NAME);
        schedulerFactoryBean.setQuartzProperties(quartzProperties);
        //延时30s
        schedulerFactoryBean.setStartupDelay(30);
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
        properties.put("org.quartz.jobStore.class","org.quartz.impl.jdbcjobstore.JobStoreTX");
        properties.put("org.quartz.jobStore.tablePrefix","QRTZ_");
        properties.put("org.quartz.jobStore.driverDelegateClass","org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        properties.put("org.quartz.jobStore.misfireThreshold", "12000");
        return properties;
    }
}
