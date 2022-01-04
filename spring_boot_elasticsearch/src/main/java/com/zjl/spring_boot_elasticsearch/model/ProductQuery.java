package com.zjl.spring_boot_elasticsearch.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author zhou
 * @version 1.0
 * @className ProductQuery
 * @description 查询参数
 * @date 2021/12/20 19:20
 **/
@Data
public class ProductQuery {

    private Integer pageNum;

    private Integer pageSize;

    private String keyword;

    private String beginDate;

    private String endDate;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private List<Map<String,String>> sortMapList;
}
