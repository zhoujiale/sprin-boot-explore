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
            //存储信息
            jobDetail.getJobDataMap().put(TackConstants.TASK_NAME,selfJobPO);
            //调度器存储任务信息和触发器
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

        try {
            //获取触发器key
            TriggerKey triggerKey = getTriggerKey(selfJobPO.getJobId(),selfJobPO.getGroupName());
            //重新构建cron
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(selfJobPO.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();
            //获取原来的触发器
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if(cronTrigger.getCronExpression().equalsIgnoreCase(selfJobPO.getCronExpression())){
                return;
            }
            //更新触发器
            cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
            //更新触发器中的调度信息
            cronTrigger.getJobDataMap().put(TackConstants.TASK_NAME,selfJobPO);
            //更新任务
            scheduler.rescheduleJob(triggerKey,cronTrigger);

        } catch (SchedulerException e) {
            ErrorLogUtil.errorLog(e);
            throw new ServiceErrorException(ServiceErrorEnum.MODIFY_JOB_ERROR);
        }

    }

    /**
     * @description 暂停定时任务
     * @author zhou
     * @create 2021/4/19 14:50
     * @param
     * @return void
     **/
    public static void pauseJob(Scheduler scheduler,SelfJobPO selfJobPO){
        try {
            scheduler.pauseJob(getJobKey(selfJobPO.getJobId(),selfJobPO.getGroupName()));
        } catch (SchedulerException e) {
            ErrorLogUtil.errorLog(e);
            throw new ServiceErrorException(ServiceErrorEnum.PAUSE_JOB_ERROR);
        }
    }

    /**
     * @description 恢复定时任务
     * @author zhou
     * @create 2021/4/19 14:56 
     * @param 
     * @return void
     **/
    public static void resumeJob(Scheduler scheduler,SelfJobPO selfJobPO){
        try {
            scheduler.resumeJob(getJobKey(selfJobPO.getJobId(),selfJobPO.getGroupName()));
        } catch (SchedulerException e) {
            ErrorLogUtil.errorLog(e);
            throw new ServiceErrorException(ServiceErrorEnum.RESUME_JOB_ERROR);
        }
    }

    /**
     * @description 删除定时任务
     * @author zhou
     * @create 2021/4/19 16:55
     * @param
     * @return void
     **/
    public static void deleteJob(Scheduler scheduler,SelfJobPO selfJobPO){
        try{
            scheduler.deleteJob(getJobKey(selfJobPO.getJobId(),selfJobPO.getGroupName()));
        }catch (SchedulerException e){
            ErrorLogUtil.errorLog(e);
            throw new ServiceErrorException(ServiceErrorEnum.DELETE_JOB_ERROR);
        }
    }

    /**
     * @description 立即执行定时任务
     * @author zhou
     * @create 2021/4/19 17:01
     * @param
     * @return void
     **/
    public static void execJob(Scheduler scheduler,SelfJobPO selfJobPO){
        try {
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put(TackConstants.TASK_NAME,selfJobPO);
            scheduler.triggerJob(getJobKey(selfJobPO.getJobId(),selfJobPO.getGroupName()),jobDataMap);
        } catch (SchedulerException e) {
            ErrorLogUtil.errorLog(e);
            throw new ServiceErrorException(ServiceErrorEnum.EXEC_JOB_ERROR);
        }
    }
}
