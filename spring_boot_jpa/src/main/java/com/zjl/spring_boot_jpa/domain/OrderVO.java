package com.zjl.spring_boot_jpa.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @name: OrderVO
 * @description:
 * @author: zhou
 * @create: 2021-02-17 14:12
 */
@Data
public class OrderVO {

    private Integer orderId;

    private BigDecimal totalPrice;

    private List<OrderDetailVO> orderDetailVOList;
}
