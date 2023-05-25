package com.zjl.spring_boot_statemachine.dao.impl;

import com.zjl.spring_boot_statemachine.dao.OrderDao;
import com.zjl.spring_boot_statemachine.enums.OrderStatusEnum;
import com.zjl.spring_boot_statemachine.model.Order;
import com.zjl.spring_boot_statemachine.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * @classname: OrderDaoImpl
 * @author: zhou
 * @description:
 * @date: 2023/5/23 17:15
 */
@Repository
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {

    private final OrderRepository orderRepository;

    @Override
    public void createOrder(String id) {
        Order order = new Order();
        order.setOrderId(id);
        order.setOrderStatus(OrderStatusEnum.WAIT_PAY.getCode());
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
    }

    @Override
    public void updateStatus(String id, Integer orderStatus) {
        Order order = orderRepository.findById(id).orElse(null);
        if (null == order){
            return;
        }
        order.setOrderStatus(orderStatus);
        order.setUpdateTime(LocalDateTime.now());
        orderRepository.saveAndFlush(order);
    }
}
