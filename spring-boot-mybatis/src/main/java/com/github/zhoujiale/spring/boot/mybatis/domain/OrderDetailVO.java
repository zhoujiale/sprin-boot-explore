package com.github.zhoujiale.spring.boot.mybatis.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @name: OrderDetailVO
 * @description:
 * @author: zhou
 * @create: 2021-02-17 14:13
 */
@Data
public class OrderDetailVO {

    private Integer orderDetailId;

    private Integer count;

    private Integer bookId;

    private BigDecimal unitPrice;
}
