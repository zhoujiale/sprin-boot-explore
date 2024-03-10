package com.github.zhoujiale.spring.boot.jpa.dao.repository;

import com.github.zhoujiale.spring.boot.jpa.model.OrderDetailPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @Auther: zhou
 * @Date: 2021-02-17 20:25
 * @Description:
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetailPO,Integer>, QuerydslPredicateExecutor<OrderDetailPO> {


}
