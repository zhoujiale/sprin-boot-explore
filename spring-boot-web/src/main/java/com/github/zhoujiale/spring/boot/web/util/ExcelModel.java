package com.github.zhoujiale.spring.boot.web.util;

import lombok.Data;

import java.util.List;

/**
 * @author zhou
 * @className ExcelModel
 * @descrption 电子表格模型类
 * @date 2022/6/13 17:30
 */
@Data
public class ExcelModel {

    /**
     * 文件名
     **/
    private String fileName;

    /**表格**/
    private List<SheetModel> sheetModelList;

    /**
     * 工作簿
     **/
    @Data
    public class SheetModel {

        private String sheetName;

        private String[] headers;

        private String[] headerTypes;

    }


}
