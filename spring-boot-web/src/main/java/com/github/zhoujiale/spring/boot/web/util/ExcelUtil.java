package com.github.zhoujiale.spring.boot.web.util;

import com.github.zhoujiale.commons.util.date.DateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhou
 * @className ExcelUtil
 * @descrption 电子表格工具类
 * @date 2022/6/13 17:00
 */
public class ExcelUtil {

    /**
     * @param name   名称
     * @param suffix 后缀
     * @return java.lang.String
     * @description 获取表格的文件名
     * @date 2022/6/13 17:10
     * @author zhou
     */
    public static String getName(String name, String suffix) {
        LocalDateTime now = LocalDateTime.now();
        String dateTimeToStr = DateUtil.localDateTimeToStr(now, "yyyy-MM-dd");
        return dateTimeToStr + name + suffix;
    }

    /**
     * @param sheet      表
     * @param sheetModel 表信息
     * @return void
     * @description 设置表头
     * @date 2022/6/13 18:11
     * @author zhou
     */
    public static void setHeader(XSSFSheet sheet, ExcelModel.SheetModel sheetModel) {
        //获取首行
        XSSFRow row = sheet.createRow(0);
        String[] headers = sheetModel.getHeaders();
        XSSFCell cell;
        for (int i = 0; i < headers.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellType(CellType.STRING);
        }
    }

    public static <T> void setData(XSSFSheet sheet, ExcelModel.SheetModel sheetModel, List<T> dataList, XSSFWorkbook workbook) throws NoSuchFieldException, IllegalAccessException {
        int rows = dataList.size();
        String[] headerTypes = sheetModel.getHeaderTypes();
        XSSFRow row;
        for (int i = 0; i < rows; i++) {
            //创建行(从第二行开始)
            row = sheet.createRow(i + 1);
            //获取这一行的数据
            T t = dataList.get(i);
            Class<?> clazz = t.getClass();
            //获取类中的字段
            Field[] declaredFields = clazz.getDeclaredFields();
            int length = declaredFields.length;
            Cell cell;
            for (int j = 0; j < length; j++) {
                cell = row.createCell(j);
                if (j == 0){
                    cell.setCellType(CellType.NUMERIC);
                    cell.setCellValue(i+1);
                }else {
                    Field field = clazz.getDeclaredField(headerTypes[j - 1]);
                    field.setAccessible(true);
                    Object o = field.get(t);
                    if(o instanceof LocalDateTime){
                        XSSFCellStyle cellStyle = workbook.createCellStyle();
                        XSSFDataFormat dataFormat = workbook.createDataFormat();
                        cell.setCellValue((LocalDateTime)o);
                        cellStyle.setDataFormat(dataFormat.getFormat("yyyy-MM-dd HH:dd:ss"));
                        cell.setCellStyle(cellStyle);
                    }else {
                        cell.setCellValue(String.valueOf(o));
                    }
                }
            }
        }
    }
}
