package com.github.zhoujiale.spring.boot.jpa.dao.impl;

import com.github.zhoujiale.spring.boot.jpa.dao.OrderDao;
import com.github.zhoujiale.spring.boot.jpa.dao.repository.OrderDetailRepository;
import com.github.zhoujiale.spring.boot.jpa.dao.repository.OrderRepository;
import com.github.zhoujiale.spring.boot.jpa.domain.OrderDTO;
import com.github.zhoujiale.spring.boot.jpa.domain.OrderDetailVO;
import com.github.zhoujiale.spring.boot.jpa.domain.OrderVO;
import com.github.zhoujiale.spring.boot.jpa.model.OrderDetailPO;
import com.github.zhoujiale.spring.boot.jpa.model.OrderPO;
import com.github.zhoujiale.spring_boot_jpa.model.QOrderDetailPO;
import com.github.zhoujiale.spring_boot_jpa.model.QOrderPO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @name: OrderDaoImpl
 * @description:
 * @author: zhou
 * @create: 2021-02-17 20:30
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public List<OrderVO> getList() {
        QOrderDetailPO orderDetailPO = QOrderDetailPO.orderDetailPO;
        QOrderPO orderPO = QOrderPO.orderPO;
        List<OrderDetailPO> orderDetailPOList = jpaQueryFactory.selectFrom(orderDetailPO)
                .innerJoin(orderPO).on(orderDetailPO.orderId.eq(orderPO.orderId))
                .where(orderPO.customerId.eq(1))
                .fetch();
        Map<Integer, List<OrderDetailPO>> collect = orderDetailPOList.stream().collect(Collectors.groupingBy(OrderDetailPO::getOrderId));
        List<OrderVO> orderVOList = new ArrayList<>(collect.size());
        for(Map.Entry<Integer,List<OrderDetailPO>> entry:collect.entrySet()){
            Integer orderId = entry.getKey();
            List<OrderDetailPO> orderDetailPOS = entry.getValue();
            List<OrderDetailVO> orderDetailVOList = new ArrayList<>(orderDetailPOS.size());
            orderDetailPOS.stream()
                    .forEach(detail->{
                        OrderDetailVO orderDetailVO = new OrderDetailVO();
                        orderDetailVO.setBookId(detail.getBookId());
                        orderDetailVO.setCount(detail.getBookCount());
                        orderDetailVO.setOrderDetailId(detail.getOrderDetailId());
                        orderDetailVO.setUnitPrice(detail.getUnitPrice());
                        orderDetailVOList.add(orderDetailVO);
                    });
            OrderVO orderVO = new OrderVO();
            orderVO.setOrderId(orderId);
            orderVO.setOrderDetailVOList(orderDetailVOList);
            orderVO.setTotalPrice(this.getTotalPrice(orderDetailPOS));
            orderVOList.add(orderVO);
        }
        return orderVOList;
    }


    @Override
    public List<OrderPO> filterPrice(BigDecimal totalPrice) {
        QOrderPO orderPO = QOrderPO.orderPO;
        Iterable<OrderPO> all = orderRepository.findAll(
                orderPO.price.gt(totalPrice)
        );
        return IterableUtils.toList(all);
    }

    @Override
    public List<OrderDTO> queryByCustomerId(Integer customerId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = criteriaBuilder.createTupleQuery();
        Root<OrderPO> root = query.from(OrderPO.class);
        query.multiselect(root.get("orderId"), root.get("price"))
                .where(
                        criteriaBuilder.and(criteriaBuilder.equal(root.get("customerId"), customerId))
                );
        List<OrderDTO> collect = entityManager.createQuery(query).getResultList()
                .stream()
                .map(tuple -> {
                    OrderDTO orderDTO = new OrderDTO();
                    orderDTO.setOrderId(tuple.get(0, Integer.class));
                    orderDTO.setPrice(tuple.get(1, BigDecimal.class));
                    return orderDTO;
                })
                .collect(Collectors.toList());
        return collect;
    }

    private BigDecimal getTotalPrice(List<OrderDetailPO> orderDetailPOList) {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderDetailPO orderDetailPO : orderDetailPOList) {
            total = total.add(orderDetailPO.getUnitPrice().multiply(BigDecimal.valueOf(orderDetailPO.getBookCount())));
        }
        return total;
    }
}
