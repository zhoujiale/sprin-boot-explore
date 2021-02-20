package com.zjl.spring_boot_jpa.service.impl;

import com.zjl.spring_boot_jpa.domain.OrderDTO;
import com.zjl.spring_boot_jpa.domain.OrderVO;
import com.zjl.spring_boot_jpa.model.OrderPO;
import com.zjl.spring_boot_jpa.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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