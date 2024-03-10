package com.github.zhoujiale.spring.boot.mybatis.service;

import com.github.zhoujiale.spring.boot.mybatis.domain.OrderVO;
import com.github.zhoujiale.spring.boot.mybatis.model.OrderDetailPO;

import java.util.List;

/**
 * @Auther: zhou
 * @Date: 2021-02-17 12:05
 * @Description:
 */
public interface OrderService {

    /**
     * @description 添加订单
     * @author zhou
     * @created  2021/2/17 12:07
     * @param
     * @return void
     **/
    void addOrder(List<OrderDetailPO> orderDetailPOList,Integer customerId);
    
    /** 
     * @description 批量获取订单 
     * @author zhou       
     * @created  2021/2/17 14:49
     * @param 
     * @return java.util.List<com.github.zhoujiale.spring_boot_mybatis.domain.OrderVO>
     **/
    List<OrderVO> getOrderVOList();
}
