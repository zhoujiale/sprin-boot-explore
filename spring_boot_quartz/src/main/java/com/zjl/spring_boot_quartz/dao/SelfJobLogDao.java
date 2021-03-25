package com.zjl.spring_boot_quartz.dao;

import com.zjl.spring_boot_quartz.model.SelfJobLogPO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author zhou
 * @className SelfJobLogDao
 * @date 2021/03/24 15:58
 * @description
 **/
public interface SelfJobLogDao {

    /**
     * @description 分页获取任务日志
     * @author zhou
     * @create 2021/3/24 19:12
     * @param jobId 任务id
     * @param pageNum 页号
     * @param pageSize 大小
     * @return org.springframework.data.domain.Page<com.zjl.spring_boot_quartz.model.SelfJobLogPO>
     **/
    Page<SelfJobLogPO> getPage(Long jobId, Integer pageNum, Integer pageSize);

    /**
     * @description 批量删除日志
     * @author zhou
     * @create 2021/3/24 19:15
     * @param logIdList 日志id
     * @return void
     **/
    void batchDelete(List<Long> logIdList);
}
