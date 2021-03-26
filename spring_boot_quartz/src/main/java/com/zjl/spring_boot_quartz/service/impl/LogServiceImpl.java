package com.zjl.spring_boot_quartz.service.impl;

import com.zjl.spring_boot_quartz.dao.SelfJobDao;
import com.zjl.spring_boot_quartz.dao.SelfJobLogDao;
import com.zjl.spring_boot_quartz.error.ServiceErrorEnum;
import com.zjl.spring_boot_quartz.error.ServiceErrorException;
import com.zjl.spring_boot_quartz.model.SelfJobLogPO;
import com.zjl.spring_boot_quartz.model.SelfJobPO;
import com.zjl.spring_boot_quartz.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhou
 * @version 1.0
 * @className LogServiceImpl
 * @description
 * @date 2021/03/24 19:02
 **/
@Slf4j
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private SelfJobDao selfJobDao;
    @Autowired
    private SelfJobLogDao selfJobLogDao;

    @Override
    public Page<SelfJobLogPO> getPage(Long jobId, Integer pageNum, Integer pageSize) {
        SelfJobPO selfJobPO = selfJobDao.getById(jobId);
        this.isJobEmpty(selfJobPO);
        Page<SelfJobLogPO> jobLogPOPage = selfJobLogDao.getPage(jobId,pageNum,pageSize);
        return jobLogPOPage;
    }

    @Override
    public void delete(List<Long> logIdList) {
        selfJobLogDao.batchDelete(logIdList);
    }

    @Override
    public void add(SelfJobLogPO selfJobPO) {
        selfJobLogDao.add(selfJobPO);
    }

    /**
     * @description 任务判空
     * @author zhou
     * @create 2021/3/24 19:10
     * @param
     * @return void
     **/
    private void isJobEmpty(SelfJobPO selfJobPO){
        if (null == selfJobPO){
            log.error("任务不存在");
            throw new ServiceErrorException(ServiceErrorEnum.JOB_EMPTY);
        }
    }
}
