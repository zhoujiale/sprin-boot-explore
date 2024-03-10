package com.github.zhoujiale.spring.boot.mybatis.service.impl;

import com.github.zhoujiale.spring.boot.mybatis.domain.OrderVO;
import com.github.zhoujiale.spring.boot.mybatis.model.OrderDetailPO;
import com.github.zhoujiale.spring.boot.mybatis.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    void addOrder() {
        List<OrderDetailPO> orderDetailPOList = new ArrayList<>();
        OrderDetailPO orderDetailPO1 = new OrderDetailPO();
        orderDetailPO1.setBookCount(2);
        orderDetailPO1.setBookId(1);
        orderDetailPO1.setUnitPrice(new BigDecimal(50));
        orderDetailPOList.add(orderDetailPO1);
        OrderDetailPO orderDetailPO2 = new OrderDetailPO();
        orderDetailPO2.setUnitPrice(new BigDecimal(20.31));
        orderDetailPO2.setBookId(2);
        orderDetailPO2.setBookCount(3);
        orderDetailPOList.add(orderDetailPO2);
        orderService.addOrder(orderDetailPOList,1);
    }

    @Test
    void getOrderVOList() {
        List<OrderVO> orderVOList = orderService.getOrderVOList();
        orderVOList.stream().forEach(orderVO -> {
            System.out.println(orderVO.toString());
            orderVO.getOrderDetailVOList().forEach(orderDetailVO -> System.out.println(orderDetailVO.toString()));
        });
    }
}