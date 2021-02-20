package com.zjl.spring_boot_jpa.service;

import com.zjl.spring_boot_jpa.domain.OrderVO;
import com.zjl.spring_boot_jpa.model.OrderPO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: zhou
 * @Date: 2021-02-17 20:26
 * @Description:
 */
public interface OrderService {

    List<OrderVO> getList();

    List<OrderPO> filterPrice(BigDecimal price);
}
