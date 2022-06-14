package com.zjl.spring_boot_web.service.impl;

import com.zjl.spring_boot_web.entity.OrderPO;
import com.zjl.spring_boot_web.repository.OrderRepository;
import com.zjl.spring_boot_web.service.ExcelService;
import com.zjl.spring_boot_web.util.ExcelModel;
import com.zjl.spring_boot_web.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhou
 * @className ExcelServiceImpl
 * @descrption 电子表格工具类
 * @date 2022/6/13 15:16
 */
@Slf4j
@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void exportExcel(HttpServletResponse response) {
        OutputStream os = null;
        ExcelModel excelModel = new ExcelModel();
        excelModel.setFileName(ExcelUtil.getName("订单", ".xlsx"));
        ExcelModel.SheetModel sheetModel = excelModel.new SheetModel();
        sheetModel.setSheetName("订单");

        List<OrderPO> orderPOList = orderRepository.findAll();
        String[] headers = {"序号", "订单号", "用户账号", "用户名", "手机", "数量", "总价", "支付价格", "订单时间", "折扣"};
        String[] headerTypes = {"orderId","customerId","customer","mobile","productCount","totalPrice","payPrice","createTime","discount"};
        List<ExcelModel.SheetModel> sheetModelList = new ArrayList<>();
        sheetModel.setHeaders(headers);
        sheetModel.setHeaderTypes(headerTypes);
        sheetModelList.add(sheetModel);
        excelModel.setSheetModelList(sheetModelList);
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(excelModel.getFileName(), "UTF-8"));
            os = response.getOutputStream();
            //创建表格文件
            XSSFWorkbook workbook = new XSSFWorkbook();
            //创建表格簿
            workbook.createSheet(excelModel.getSheetModelList().get(0).getSheetName());
            XSSFSheet sheet = workbook.getSheetAt(0);
            //设置表头
            ExcelUtil.setHeader(sheet, excelModel.getSheetModelList().get(0));
            //设置表内容
            ExcelUtil.setData(sheet, excelModel.getSheetModelList().get(0), orderPOList,workbook);

            workbook.write(os);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return;
        } finally {
            try {
                os.flush();
                os.close();
            } catch (Exception e) {

            }
        }

    }

    @Override
    public void easyExcel(HttpServletResponse response) {
        ExcelModel excelModel = new ExcelModel();
        String name = ExcelUtil.getName("订单", ".xlsx");
        excelModel.setFileName(name);
    }
}
