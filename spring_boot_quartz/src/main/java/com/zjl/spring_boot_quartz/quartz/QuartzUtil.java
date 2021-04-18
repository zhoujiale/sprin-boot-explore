package com.zjl.spring_boot_quartz.quartz;

import com.zjl.commons.util.log.ErrorLogUtil;
import com.zjl.spring_boot_quartz.error.ServiceErrorEnum;
import com.zjl.spring_boot_quartz.error.ServiceErrorException;
import com.zjl.spring_boot_quartz.model.SelfJobPO;
import org.quartz.*;

/**
 * @name: QuartzUtil
 * @description: 任务工具类
 * @author: zhou
 * @create: 2021-03-25 23:48
 */
public class QuartzUtil {

    private static final String KEY = "TASK_";

    public static JobKey getJobKey(Long jobId,String group){
        return JobKey.jobKey(KEY+jobId,group);
    }

    public static TriggerKey getTriggerKey(Long jobId,String group){
        return TriggerKey.triggerKey(KEY+jobId,group);
    }

    /**
     * @description 创建定时任务
     * @author zhou
     * @created  2021/4/18 21:50
     * @param scheduler 调度
     * @param selfJobPO 自定义任务信息
     * @return void
     **/
    public static void createJob(Scheduler scheduler, SelfJobPO selfJobPO){
        try{
            //job信息
            JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class).withIdentity(getJobKey(selfJobPO.getJobId(), selfJobPO.getGroupName()))
                    .build();
            //cron
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(selfJobPO.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();
            //触发器
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(selfJobPO.getJobId(), selfJobPO.getGroupName()))
                    .withSchedule(cronScheduleBuilder).build();
            jobDetail.getJobDataMap().put(SelfJobPO.JOB_PARAM_KEY,selfJobPO);

            scheduler.scheduleJob(jobDetail,cronTrigger);
        }catch (SchedulerException e){
            ErrorLogUtil.errorLog(e);
            throw new ServiceErrorException(ServiceErrorEnum.ADD_JOB_ERROR);
        }
    }

    /** 
     * @description 更新定时任务 
     * @author zhou       
     * @created  2021/4/18 23:19
     * @param 
     * @return void
     **/
    public static void updateJob(Scheduler scheduler,SelfJobPO selfJobPO){
        
    }
}
