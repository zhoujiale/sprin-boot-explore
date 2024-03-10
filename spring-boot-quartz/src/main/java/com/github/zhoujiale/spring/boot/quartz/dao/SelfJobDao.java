package com.github.zhoujiale.spring.boot.quartz.dao;

import com.github.zhoujiale.spring.boot.quartz.model.SelfJobPO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author zhou
 * @className SelfJobDao
 * @date 2021/03/24 15:42
 * @description
 **/
public interface SelfJobDao {

    /**
     * @description 添加任务
     * @author zhou
     * @create 2021/3/24 17:10
     * @param
     * @return com.github.zhoujiale.spring_boot_quartz.model.SelfJobPO
     **/
    SelfJobPO add(SelfJobPO selfJobPO);

    /**
     * @description 更新任务
     * @author zhou
     * @create 2021/3/24 17:17
     * @param selfJobPO
     * @return void
     **/
    void modify(SelfJobPO selfJobPO);

    /**
     * @description 删除任务
     * @author zhou
     * @create 2021/3/24 17:21 
     * @param 
     * @return void
     **/
    void delete(Long jobId);

    /**
     * @description 分页获取任务列表
     * @author zhou
     * @create 2021/3/24 17:25 
     * @param 
     * @return org.springframework.data.domain.Page<com.github.zhoujiale.spring_boot_quartz.model.SelfJobPO>
     **/
    Page<SelfJobPO> getPage(Integer pageNum, Integer pageSize, String name);

    /**
     * @description 通过id获取任务
     * @author zhou
     * @create 2021/3/24 19:08
     * @param jobId 任务id
     * @return com.github.zhoujiale.spring_boot_quartz.model.SelfJobPO
     **/
    SelfJobPO getById(Long jobId);

    /**
     * @description 获取所有现存的服务
     * @author zhou
     * @create 2021/4/20 10:30 
     * @param 
     * @return java.util.List<com.github.zhoujiale.spring_boot_quartz.model.SelfJobPO>
     **/
    List<SelfJobPO> getAllJob();
}
