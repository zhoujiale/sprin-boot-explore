package com.github.zhoujiale.spring.boot.quartz.controller;

import com.github.zhoujiale.commons.util.response.WebResponse;
import com.github.zhoujiale.spring.boot.quartz.error.ServiceErrorEnum;
import com.github.zhoujiale.spring.boot.quartz.error.ServiceErrorException;
import com.github.zhoujiale.spring.boot.quartz.model.SelfJobLogPO;
import com.github.zhoujiale.spring.boot.quartz.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhou
 * @version 1.0
 * @className QuartzLogController
 * @description
 * @date 2021/03/24 18:53
 **/
@Slf4j
@Api(value = "任务日志",tags = {"任务日志"})
@RestController
@RequestMapping(value = "/log")
public class QuartzLogController {

    @Autowired
    private LogService logService;

    @ApiOperation(value = "日志列表")
    @GetMapping(value = "/list")
    public WebResponse list(@RequestParam(value = "jobId")Long jobId,
                            @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                            @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        Page<SelfJobLogPO> jobLogPOPage = logService.getPage(jobId,pageNum,pageSize);
        return WebResponse.success(jobLogPOPage);
    }

    @ApiOperation(value = "删除日志")
    @DeleteMapping(value = "/delete")
    public WebResponse delete(@RequestParam(value = "ids")Long[] ids){
        if (null == ids || ids.length == 0){
            log.error("请选择日志");
            throw new ServiceErrorException(ServiceErrorEnum.LOG_EMPTY);
        }
        List<Long> logIdList = new ArrayList<>(Arrays.asList(ids));
        logService.delete(logIdList);
        return WebResponse.success();
    }
}
