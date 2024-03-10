package com.github.zhoujiale.spring.boot.mybatis.service.impl;

import com.github.zhoujiale.spring.boot.mybatis.domain.OrderDetailVO;
import com.github.zhoujiale.spring.boot.mybatis.domain.OrderVO;
import com.github.zhoujiale.spring.boot.mybatis.mapper.OrderDetailPODynamicSqlSupport;
import com.github.zhoujiale.spring.boot.mybatis.mapper.OrderDetailPOMapper;
import com.github.zhoujiale.spring.boot.mybatis.mapper.OrderPODynamicSqlSupport;
import com.github.zhoujiale.spring.boot.mybatis.mapper.OrderPOMapper;
import com.github.zhoujiale.spring.boot.mybatis.model.OrderDetailPO;
import com.github.zhoujiale.spring.boot.mybatis.model.OrderPO;
import com.github.zhoujiale.spring.boot.mybatis.service.OrderService;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.select.join.EqualTo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @name: OrderServiceImpl
 * @description:
 * @author: zhou
 * @create: 2021-02-17 12:05
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderPOMapper orderPOMapper;
    @Resource
    private OrderDetailPOMapper orderDetailPOMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addOrder(List<OrderDetailPO> orderDetailPOList,Integer customerId) {
        OrderPO orderPO = new OrderPO();
        orderPO.setCustomerId(customerId);
        orderPO.setPrice(this.getTotalPrice(orderDetailPOList));
        orderPOMapper.insertSelective(orderPO);
        orderDetailPOList.forEach(orderDetailPO -> orderDetailPO.setOrderId(orderPO.getOrderId()));
        orderDetailPOList.forEach(orderDetailPO -> orderDetailPOMapper.insertSelective(orderDetailPO));
    }

    @Override
    public List<OrderVO> getOrderVOList() {
        List<OrderDetailPO> select = orderDetailPOMapper.select(selectModelQueryExpressionDSL ->
                selectModelQueryExpressionDSL.join(SqlTable.of("tb_order"))
                        .on(OrderDetailPODynamicSqlSupport.orderId, new EqualTo(OrderPODynamicSqlSupport.orderId))
                        .where(OrderPODynamicSqlSupport.customerId, isEqualTo(1))
        );
        List<OrderVO> orderVOList = new ArrayList<>();
        Map<Integer, List<OrderDetailPO>> collect = select.stream().collect(Collectors.groupingBy(OrderDetailPO::getOrderId));
        for(Map.Entry<Integer,List<OrderDetailPO>> entry: collect.entrySet()){
            Integer orderId = entry.getKey();
            List<OrderDetailPO> value = entry.getValue();
            List<OrderDetailVO> orderDetailVOList = new ArrayList<>(value.size());
            for (OrderDetailPO orderDetailPO : value) {
                OrderDetailVO orderDetailVO = new OrderDetailVO();
                orderDetailVO.setBookId(orderDetailPO.getBookId());
                orderDetailVO.setCount(orderDetailPO.getBookCount());
                orderDetailVO.setOrderDetailId(orderDetailPO.getOrderDetailId());
                orderDetailVO.setUnitPrice(orderDetailPO.getUnitPrice());
                orderDetailVOList.add(orderDetailVO);
            }
            OrderVO orderVO = new OrderVO();
            orderVO.setOrderId(orderId);
            orderVO.setOrderDetailVOList(orderDetailVOList);
            orderVO.setTotalPrice(this.getTotalPrice(value));
            orderVOList.add(orderVO);
        }
        return orderVOList;
    }

    private BigDecimal getTotalPrice(List<OrderDetailPO> orderDetailPOList) {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderDetailPO orderDetailPO : orderDetailPOList) {
            total = total.add(orderDetailPO.getUnitPrice().multiply(BigDecimal.valueOf(orderDetailPO.getBookCount())));
        }
        return total;
    }
}
