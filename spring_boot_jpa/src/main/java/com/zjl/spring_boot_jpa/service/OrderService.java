package com.zjl.spring_boot_jpa.service;

import com.zjl.spring_boot_jpa.domain.OrderVO;

import java.util.List;

/**
 * @Auther: zhou
 * @Date: 2021-02-17 20:26
 * @Description:
 */
public interface OrderService {

    List<OrderVO> getList();
}
