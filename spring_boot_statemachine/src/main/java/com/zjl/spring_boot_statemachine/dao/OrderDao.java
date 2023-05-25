package com.zjl.spring_boot_statemachine.dao;

/**
 * @classname: OrderDao
 * @author: zhou
 * @description:
 * @date: 2023/5/23 17:15
 */
public interface OrderDao {

    void createOrder(String id);

    void updateStatus(String id,Integer orderStatus);
}
