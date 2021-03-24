package com.zjl.spring_boot_quartz.controller;

import com.zjl.commons.util.response.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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

    @ApiOperation(value = "获取任务列表")
    @GetMapping(value = "/list")
    public WebResponse list(){
        return WebResponse.success();
    }

    @ApiOperation(value = "添加任务")
    @PostMapping(value = "/add")
    public WebResponse add(){
        return WebResponse.success();
    }

    @ApiOperation(value = "编辑任务")
    @PostMapping(value = "/update")
    public WebResponse update(){
        return WebResponse.success();
    }

    @ApiOperation(value = "删除任务")
    @DeleteMapping(value = "/delete")
    public WebResponse delete(){
        return WebResponse.success();
    }
}
