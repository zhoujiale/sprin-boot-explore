package com.github.zhoujiale.spring.boot.jpa.service;

import com.github.zhoujiale.spring.boot.jpa.domain.OrderDTO;
import com.github.zhoujiale.spring.boot.jpa.domain.OrderVO;
import com.github.zhoujiale.spring.boot.jpa.model.OrderPO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: zhou
 * @Date: 2021-02-17 20:26
 * @Description:
 */
public interface OrderService {

    List<OrderVO> getList();

    List<OrderPO> filterPrice(BigDecimal price);

    List<OrderDTO> queryByCustomerId(Integer customerId);
}
