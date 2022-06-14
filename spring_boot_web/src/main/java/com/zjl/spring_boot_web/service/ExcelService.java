package com.zjl.spring_boot_web.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhou
 * @className ExcelService
 * @descrption 电子表格
 * @date 2022/6/13 15:15
 */
public interface ExcelService {

    /**
     * @description 导出excel
     * @date 2022/6/13 16:56
     * @author zhou
     * @param response
     * @return void
     */
    void exportExcel(HttpServletResponse response);

    /**
     * @description 导出excel
     * @date 2022/6/13 21:53
     * @author zhou
     * @param response
     * @return void
     */
    void easyExcel(HttpServletResponse response);
}
