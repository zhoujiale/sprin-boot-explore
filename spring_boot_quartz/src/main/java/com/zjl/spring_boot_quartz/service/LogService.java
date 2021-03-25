package com.zjl.spring_boot_quartz.service;

import com.zjl.spring_boot_quartz.model.SelfJobLogPO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author zhou
 * @className LogService
 * @date 2021/03/24 19:02
 * @description
 **/
public interface LogService {

    /**
     * @description 分页获取定时任务的执行日志
     * @author zhou
     * @create 2021/3/24 19:03
     * @param jobId 任务id
     * @param pageNum 页号
     * @param pageSize 大小
     * @return org.springframework.data.domain.Page<com.zjl.spring_boot_quartz.model.SelfJobLogPO>
     **/
    Page<SelfJobLogPO> getPage(Long jobId, Integer pageNum, Integer pageSize);

    /**
     * @description 删除日志
     * @author zhou
     * @create 2021/3/24 19:06
     * @param logIdList
     * @return void
     **/
    void delete(List<Long> logIdList);
}
