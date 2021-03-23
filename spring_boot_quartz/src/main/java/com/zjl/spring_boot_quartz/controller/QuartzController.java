package com.zjl.spring_boot_quartz.controller;

import com.zjl.commons.util.response.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @name: QuartzController
 * @description:
 * @author: zhou
 * @create: 2021-03-23 22:55
 */
@Api(value = "任务模块",tags = {"任务模块"})
@Slf4j
@RestController
@RequestMapping(value = "/api")
public class QuartzController {

    @ApiOperation(value = "任务列表")
    @GetMapping(value = "/list")
    public WebResponse list(){
        return WebResponse.success();
    }

    @ApiOperation(value = "添加任务")
    @PostMapping(value = "/add")
    public WebResponse addSchedule(){
        return WebResponse.success();
    }

    @ApiOperation(value = "更新任务")
    @PostMapping(value = "/update")
    public WebResponse updateSchedule(){
        return WebResponse.success();
    }

    @ApiOperation(value = "删除任务")
    @DeleteMapping(value = "/delete")
    public WebResponse deleteSchedule(){
        return WebResponse.success();
    }
}
