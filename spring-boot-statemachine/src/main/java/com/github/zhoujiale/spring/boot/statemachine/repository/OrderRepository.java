package com.github.zhoujiale.spring.boot.statemachine.repository;

import com.github.zhoujiale.spring.boot.statemachine.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @classname: OrderRepository
 * @author: zhou
 * @description:
 * @date: 2023/5/23 16:56
 */
public interface OrderRepository extends JpaRepository<Order,String> {

}
