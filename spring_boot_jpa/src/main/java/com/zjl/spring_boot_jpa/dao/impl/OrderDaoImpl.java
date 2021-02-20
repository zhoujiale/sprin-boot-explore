package com.zjl.spring_boot_jpa.dao.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zjl.spring_boot_jpa.dao.OrderDao;
import com.zjl.spring_boot_jpa.dao.repository.OrderDetailRepository;
import com.zjl.spring_boot_jpa.dao.repository.OrderRepository;
import com.zjl.spring_boot_jpa.domain.OrderVO;
import com.zjl.spring_boot_jpa.model.OrderDetailPO;
import com.zjl.spring_boot_jpa.model.OrderPO;
import com.zjl.spring_boot_jpa.model.QOrderPO;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

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
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderDetailPO> query = criteriaBuilder.createQuery(OrderDetailPO.class);
        Root<OrderDetailPO> root = query.from(OrderDetailPO.class);
        OrderDetailPO orderDetailPO = new OrderDetailPO();

        return null;
    }

    @Override
    public List<OrderPO> filterPrice(BigDecimal totalPrice) {
        QOrderPO orderPO = QOrderPO.orderPO;
        Iterable<OrderPO> all = orderRepository.findAll(
                orderPO.price.gt(totalPrice)
        );
        return IterableUtils.toList(all);
    }
}
