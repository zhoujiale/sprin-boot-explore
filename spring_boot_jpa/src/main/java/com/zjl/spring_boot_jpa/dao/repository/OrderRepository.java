package com.zjl.spring_boot_jpa.dao.repository;

import com.zjl.spring_boot_jpa.model.OrderPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: zhou
 * @Date: 2021-02-17 19:57
 * @Description:
 */
public interface OrderRepository extends JpaRepository<OrderPO,Integer>,
        JpaSpecificationExecutor<OrderPO>, QuerydslPredicateExecutor<OrderPO> {

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update OrderPO set price = :#{#orderPO.price} where orderId = :#{#orderPO.orderId}")
    int updateOrder(@Param("orderPO")OrderPO orderPO);
}
