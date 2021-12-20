package com.zjl.spring_boot_elasticsearch.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhou
 * @version 1.0
 * @className ProductVO
 * @description 商品响应
 * @date 2021/12/20 19:25
 **/
@Data
public class ProductVO {

    private String id;

    private String name;

    private BigDecimal price;
}
