package com.github.zhoujiale.spring.boot.web.controller;

import com.github.zhoujiale.commons.util.response.WebResponse;
import com.github.zhoujiale.spring.boot.web.service.ExcelService;
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
    public WebResponse<Void> exportFile(HttpServletResponse response){
        log.info("excel export begin");
        excelService.exportExcel(response);
        return WebResponse.success();
    }
}
