package com.zjl.spring_boot_quartz.service.impl;

import com.zjl.spring_boot_quartz.dao.SelfJobDao;
import com.zjl.spring_boot_quartz.domain.OperateEnum;
import com.zjl.spring_boot_quartz.error.ServiceErrorEnum;
import com.zjl.spring_boot_quartz.error.ServiceErrorException;
import com.zjl.spring_boot_quartz.model.SelfJobPO;
import com.zjl.spring_boot_quartz.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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

    @Override
    public void add(SelfJobPO selfJobPO) {
        this.validSelfJob(selfJobPO,OperateEnum.ADD);
        selfJobDao.add(selfJobPO);
    }

    @Override
    public void update(SelfJobPO selfJobPO) {
        this.validSelfJob(selfJobPO,OperateEnum.MODIFY);
        selfJobDao.modify(selfJobPO);
    }

    @Override
    public void delete(Long jobId) {
        if (null == jobId){
            log.error("缺少jobId");
            throw new ServiceErrorException(ServiceErrorEnum.JOB_ID);
        }
        selfJobDao.delete(jobId);
    }

    @Override
    public Page<SelfJobPO> getPage(Integer pageNum, Integer pageSize, String name) {
        return selfJobDao.getPage(pageNum,pageSize,name);
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
}
