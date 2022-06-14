package com.zjl.spring_boot_web.controller;

import com.zjl.commons.util.response.WebResponse;
import com.zjl.spring_boot_web.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhou
 * @className ExcelController
 * @descrption 电子表格
 * @date 2022/6/13 14:48
 */
@Slf4j
@RestController
@RequestMapping(value = "/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @GetMapping(value = "/export")
    public WebResponse exportFile(HttpServletResponse response){
        log.info("excel export begin");
        excelService.exportExcel(response);
        return WebResponse.success();
    }
}
