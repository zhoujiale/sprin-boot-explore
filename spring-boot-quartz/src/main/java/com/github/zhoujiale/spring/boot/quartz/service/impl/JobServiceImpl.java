package com.github.zhoujiale.spring.boot.quartz.service.impl;

import com.github.zhoujiale.commons.util.valid.OperateEnum;
import com.github.zhoujiale.spring.boot.quartz.dao.SelfJobDao;
import com.github.zhoujiale.spring.boot.quartz.domain.StateEnum;
import com.github.zhoujiale.spring.boot.quartz.error.ServiceErrorEnum;
import com.github.zhoujiale.spring.boot.quartz.error.ServiceErrorException;
import com.github.zhoujiale.spring.boot.quartz.model.SelfJobPO;
import com.github.zhoujiale.spring.boot.quartz.quartz.QuartzUtil;
import com.github.zhoujiale.spring.boot.quartz.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zhou
 * @version 1.0
 * @className JobServiceImpl
 * @description
 * @date 2021/03/24 16:29
 **/
@Slf4j
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private SelfJobDao selfJobDao;

    @Resource
    private Scheduler scheduler;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addJob(SelfJobPO selfJobPO) {
        this.validSelfJob(selfJobPO,OperateEnum.ADD);
        selfJobDao.add(selfJobPO);
        QuartzUtil.createJob(scheduler,selfJobPO);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateJob(SelfJobPO selfJobPO) {
        this.validSelfJob(selfJobPO,OperateEnum.MODIFY);
        selfJobDao.modify(selfJobPO);
        QuartzUtil.updateJob(scheduler,selfJobPO);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteJob(Long jobId) {
        SelfJobPO selfJobPO = selfJobDao.getById(jobId);
        this.selfJobIsEmpty(selfJobPO);
        selfJobDao.delete(jobId);
        QuartzUtil.deleteJob(scheduler,selfJobPO);
    }

    @Override
    public Page<SelfJobPO> getPage(Integer pageNum, Integer pageSize, String name) {
        return selfJobDao.getPage(pageNum,pageSize,name);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void pauseJob(Long jobId) {
        SelfJobPO selfJobPO = selfJobDao.getById(jobId);
        this.selfJobIsEmpty(selfJobPO);
        if (StateEnum.PAUSE.getCode().equals(selfJobPO.getJobStatus())){
            log.warn("任务已经暂停了");
            throw new ServiceErrorException(ServiceErrorEnum.JOB_IS_PAUSE);
        }
        selfJobPO.setJobStatus(StateEnum.PAUSE.getCode());
        selfJobDao.modify(selfJobPO);
        QuartzUtil.pauseJob(scheduler,selfJobPO);
    }

    @Override
    public void runJob(Long jobId) {
        SelfJobPO selfJobPO = selfJobDao.getById(jobId);
        this.selfJobIsEmpty(selfJobPO);
        if (StateEnum.PAUSE.getCode().equals(selfJobPO.getJobStatus())){
            selfJobPO.setJobStatus(StateEnum.RUN.getCode());
        }
        selfJobDao.modify(selfJobPO);
        QuartzUtil.execJob(scheduler,selfJobPO);
    }

    @Override
    public void restoreJob(Long jobId) {
        SelfJobPO selfJobPO = selfJobDao.getById(jobId);
        this.selfJobIsEmpty(selfJobPO);
        if (StateEnum.RUN.getCode().equals(selfJobPO.getJobStatus())){
            log.warn("任务已经在运行了");
            throw new ServiceErrorException(ServiceErrorEnum.JOB_IS_RUNNING);
        }
        selfJobPO.setJobStatus(StateEnum.RUN.getCode());
        selfJobDao.modify(selfJobPO);
        QuartzUtil.resumeJob(scheduler,selfJobPO);
    }

    private void validSelfJob(SelfJobPO selfJobPO, OperateEnum operateEnum){
        if (OperateEnum.MODIFY.equals(operateEnum)){
            if (null == selfJobPO.getJobId()){
                log.error("缺少jobId");
                throw new ServiceErrorException(ServiceErrorEnum.JOB_ID);
            }
        }
        if (StringUtils.isBlank(selfJobPO.getBeanName())){
            log.error("缺少java_bean");
            throw new ServiceErrorException(ServiceErrorEnum.JAVA_BEAN);
        }
        if (StringUtils.isBlank(selfJobPO.getJobName())){
            log.error("缺少任务名称");
            throw new ServiceErrorException(ServiceErrorEnum.JOB_NAME);
        }
        if (StringUtils.isBlank(selfJobPO.getCronExpression())){
            log.error("缺少cron表达式");
            throw new ServiceErrorException(ServiceErrorEnum.CORN);
        }
    }

    private void selfJobIsEmpty(SelfJobPO selfJobPO){
        if(null == selfJobPO){
            log.error("定时任务不存在");
            throw new ServiceErrorException(ServiceErrorEnum.JOB_EMPTY);
        }
    }
}
