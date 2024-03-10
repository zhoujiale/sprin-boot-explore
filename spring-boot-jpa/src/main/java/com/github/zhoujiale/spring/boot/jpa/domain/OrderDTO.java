package com.github.zhoujiale.spring.boot.jpa.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhou
 * @version 1.0
 * @className OrderDTO
 * @description
 * @date 2021/02/20 15:05
 **/
@Data
public class OrderDTO {


    private Integer orderId;

    private BigDecimal price;
}
