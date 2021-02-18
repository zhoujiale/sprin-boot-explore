package com.zjl.spring_boot_jpa.service.impl;

import com.zjl.spring_boot_jpa.dao.OrderDao;
import com.zjl.spring_boot_jpa.domain.OrderVO;
import com.zjl.spring_boot_jpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @name: OrderServiceImpl
 * @description:
 * @author: zhou
 * @create: 2021-02-17 20:27
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<OrderVO> getList() {
        return orderDao.getList();
    }
}
