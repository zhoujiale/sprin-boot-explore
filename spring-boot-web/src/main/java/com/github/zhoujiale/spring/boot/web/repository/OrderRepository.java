package com.github.zhoujiale.spring.boot.web.repository;

import com.github.zhoujiale.spring.boot.web.entity.OrderPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Auther: zhou
 * @Date: 2021-02-17 19:57
 * @Description:
 */
public interface OrderRepository extends JpaRepository<OrderPO,Integer>,
        JpaSpecificationExecutor<OrderPO> {


}
