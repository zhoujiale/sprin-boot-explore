package com.zjl.spring_boot_web.repository;

import com.zjl.spring_boot_web.entity.OrderPO;
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
