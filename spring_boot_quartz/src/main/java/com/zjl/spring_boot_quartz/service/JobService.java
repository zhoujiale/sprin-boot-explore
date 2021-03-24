package com.zjl.spring_boot_quartz.service;

import com.zjl.spring_boot_quartz.model.SelfJobPO;
import org.springframework.data.domain.Page;

/**
 * @author zhou
 * @className JobService
 * @date 2021/03/24 16:29
 * @description
 **/
public interface JobService {

    /**
     * @description 添加任务
     * @author zhou
     * @create 2021/3/24 16:30
     * @param selfJobPO
     * @return void
     **/
    void add(SelfJobPO selfJobPO);

    /**
     * @description 编辑任务
     * @author zhou
     * @create 2021/3/24 17:15
     * @param selfJobPO
     * @return void
     **/
    void update(SelfJobPO selfJobPO);

    /**
     * @description 删除任务
     * @author zhou
     * @create 2021/3/24 17:20
     * @param
     * @return void
     **/
    void delete(Long jobId);

    /**
     * @description 获取分页的任务列表
     * @author zhou
     * @create 2021/3/24 19:02
     * @param 
     * @return org.springframework.data.domain.Page<com.zjl.spring_boot_quartz.model.SelfJobPO>
     **/
    Page<SelfJobPO> getPage(Integer pageNum, Integer pageSize, String name);
}
