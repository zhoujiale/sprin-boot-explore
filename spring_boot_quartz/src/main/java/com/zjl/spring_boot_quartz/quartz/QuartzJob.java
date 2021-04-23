package com.zjl.spring_boot_quartz.quartz;

import com.zjl.commons.util.log.ErrorLogUtil;
import com.zjl.spring_boot_quartz.domain.ExecuteEnum;
import com.zjl.spring_boot_quartz.model.SelfJobLogPO;
import com.zjl.spring_boot_quartz.model.SelfJobPO;
import com.zjl.spring_boot_quartz.service.LogService;
import com.zjl.spring_boot_quartz.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @name: QuartzJob
 * @description: 自定义任务
 * @author: zhou
 * @create: 2021-03-25 23:20
 */
@Slf4j
@Component
public class QuartzJob extends QuartzJobBean {



    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SelfJobLogPO logPO = new SelfJobLogPO();
        long startTime = System.currentTimeMillis();
        logPO.setCreateDate(LocalDateTime.ofEpochSecond(startTime/1000,0, ZoneOffset.ofHours(0)));
        log.warn(logPO.getCreateDate().toString());
        SelfJobPO selfJobPO = null;
        LogService logService = SpringContextUtil.getBean(LogService.class);
        try {
            selfJobPO = (SelfJobPO) jobExecutionContext.getMergedJobDataMap().get(TackConstants.TASK_NAME);
            log.debug("定时任务开始执行,jobId:[{}]", selfJobPO.getJobId());
            this.execute(selfJobPO);
            logPO.setJobId(selfJobPO.getJobId());
            logPO.setExecuteStatus(ExecuteEnum.SUCCESS.getCode());
            logPO.setError(StringUtils.EMPTY);
        } catch (Exception e) {
            ErrorLogUtil.errorLog(e);
            log.error("定时任务执行失败,jobId:[{}]", selfJobPO.getJobId());
            logPO.setExecuteStatus(ExecuteEnum.FAIL.getCode());
            logPO.setError(StringUtils.substring(e.toString(), 0, 500));
        } finally {
            long useTime = System.currentTimeMillis() - startTime;
            log.debug("定时任务执行结束,jobId[{}],耗时:[{}]毫秒", selfJobPO.getJobId(), useTime);
            logPO.setUserTime(useTime);
            logService.add(logPO);
        }
    }

    /**
     * @param selfJobPO 定时任务模型
     * @return void
     * @description 反射执行定时任务方法
     * @author zhou
     * @create 2021/3/26 13:26
     **/
    private void execute(SelfJobPO selfJobPO) throws Exception {
        Object bean = SpringContextUtil.getBean(selfJobPO.getBeanName());
        Method method = bean.getClass().getDeclaredMethod(selfJobPO.getMethodName(), String.class);
        method.invoke(bean, selfJobPO.getParams());
    }
}
