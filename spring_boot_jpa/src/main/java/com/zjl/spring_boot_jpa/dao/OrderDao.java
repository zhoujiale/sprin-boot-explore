package com.zjl.spring_boot_jpa.dao;

import com.zjl.spring_boot_jpa.domain.OrderDTO;
import com.zjl.spring_boot_jpa.domain.OrderVO;
import com.zjl.spring_boot_jpa.model.OrderPO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: zhou
 * @Date: 2021-02-17 19:56
 * @Description:
 */
public interface OrderDao {

    List<OrderDTO> queryByCustomerId(Integer customerId);

    List<OrderVO> getList();

    List<OrderPO> filterPrice(BigDecimal totalPrice);
}
