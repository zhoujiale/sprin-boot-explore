package com.zjl.spring_boot_quartz.controller;

import com.zjl.commons.util.response.WebResponse;
import com.zjl.spring_boot_quartz.model.SelfJobPO;
import com.zjl.spring_boot_quartz.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhou
 * @version 1.0
 * @className QuartzController
 * @description
 * @date 2021/03/24 11:03
 **/
@Slf4j
@Api(value = "任务模块",tags = {"任务模块"})
@RestController
@RequestMapping(value = "/api")
public class QuartzController {

    @Autowired
    private JobService jobService;

    @ApiOperation(value = "获取任务列表")
    @GetMapping(value = "/list")
    public WebResponse list(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                            @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                            @RequestParam(value = "name",required = false)String name){
        Page<SelfJobPO> jobPOPage = jobService.getPage(pageNum,pageSize,name);
        return WebResponse.success(jobPOPage);
    }

    @ApiOperation(value = "添加任务")
    @PostMapping(value = "/add")
    public WebResponse add(@RequestBody SelfJobPO selfJobPO){
        jobService.addJob(selfJobPO);
        return WebResponse.success();
    }

    @ApiOperation(value = "编辑任务")
    @PostMapping(value = "/update")
    public WebResponse update(@RequestBody SelfJobPO selfJobPO){
        jobService.updateJob(selfJobPO);
        return WebResponse.success();
    }

    @ApiOperation(value = "删除任务")
    @DeleteMapping(value = "/delete")
    public WebResponse delete(@RequestParam(value = "jobId")Long jobId){
        jobService.deleteJob(jobId);
        return WebResponse.success();
    }

    @ApiOperation(value = "暂停任务")
    @PostMapping(value = "/pause")
    public WebResponse pause(@RequestParam(value = "jobId")Long jobId){
        jobService.pauseJob(jobId);
        return WebResponse.success();
    }

    @ApiOperation(value = "恢复任务")
    @PostMapping(value = "/resume")
    public WebResponse restore(@RequestParam(value = "jobId")Long jobId){
        jobService.restoreJob(jobId);
        return WebResponse.success();
    }

    @ApiOperation(value = "立即执行任务")
    @PostMapping(value = "/exec")
    public WebResponse exec(@RequestParam(value = "jobId")Long jobId){
        jobService.runJob(jobId);
        return WebResponse.success();
    }

}
