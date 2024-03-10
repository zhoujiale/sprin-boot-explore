package com.github.zhoujiale.spring.boot.jpa.service.impl;

import com.github.zhoujiale.spring.boot.jpa.domain.OrderDTO;
import com.github.zhoujiale.spring.boot.jpa.domain.OrderVO;
import com.github.zhoujiale.spring.boot.jpa.model.OrderPO;
import com.github.zhoujiale.spring.boot.jpa.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    void filterPrice() {
        List<OrderPO> orderPOS = orderService.filterPrice(new BigDecimal(80));
        orderPOS.forEach(orderPO -> System.out.println(orderPO.toString()));
    }

    @Test
    void queryByCustomerId() {
        List<OrderDTO> orderDTOS = orderService.queryByCustomerId(1);
        orderDTOS.forEach(orderDTO -> System.out.println(orderDTO.toString()));
    }

    @Test
    void getList() {
        List<OrderVO> list = orderService.getList();
        list.forEach(i-> System.out.println(i.toString()));
    }
}