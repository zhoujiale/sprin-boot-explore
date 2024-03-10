package com.github.zhoujiale.spring.boot.jpa.dao;

import com.github.zhoujiale.spring.boot.jpa.domain.OrderDTO;
import com.github.zhoujiale.spring.boot.jpa.domain.OrderVO;
import com.github.zhoujiale.spring.boot.jpa.model.OrderPO;

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
