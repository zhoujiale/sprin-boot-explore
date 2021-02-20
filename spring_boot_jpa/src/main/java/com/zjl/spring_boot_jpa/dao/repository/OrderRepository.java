package com.zjl.spring_boot_jpa.dao.repository;

import com.zjl.spring_boot_jpa.model.OrderPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @Auther: zhou
 * @Date: 2021-02-17 19:57
 * @Description:
 */
public interface OrderRepository extends JpaRepository<OrderPO,Integer>,
        JpaSpecificationExecutor<OrderPO>, QuerydslPredicateExecutor<OrderPO> {
}
