package com.zjl.spring_boot_quartz.dao.impl;

import com.zjl.spring_boot_quartz.dao.SelfJobLogDao;
import com.zjl.spring_boot_quartz.dao.repository.SelfJobLogRepository;
import com.zjl.spring_boot_quartz.model.SelfJobLogPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhou
 * @version 1.0
 * @className SelfJobLogDaoImpl
 * @description
 * @date 2021/03/24 15:59
 **/
@Repository
public class SelfJobLogDaoImpl implements SelfJobLogDao {

    @Autowired
    private SelfJobLogRepository selfJobLogRepository;

    @Override
    public Page<SelfJobLogPO> getPage(Long jobId, Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.DESC, "createDate"));
        SelfJobLogPO selfJobLogPO = new SelfJobLogPO();
        selfJobLogPO.setJobId(jobId);
        return selfJobLogRepository.findAll(Example.of(selfJobLogPO),pageRequest);
    }

    @Override
    public void batchDelete(List<Long> logIdList) {
        selfJobLogRepository.deleteSelfJobLogPOSByLogIdIn(logIdList);
    }

    @Override
    public void add(SelfJobLogPO selfJobPO) {
        selfJobLogRepository.save(selfJobPO);
    }
}
