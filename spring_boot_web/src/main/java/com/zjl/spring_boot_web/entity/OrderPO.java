package com.zjl.spring_boot_web.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @name: OrderPO
 * @description:
 * @author: zhou
 * @create: 2021-02-12 15:36
 */
@Data
@Entity
@Table(name = "book_order")
@org.hibernate.annotations.Table(appliesTo = "book_order",comment = "订单表")
@EntityListeners(AuditingEntityListener.class)
public class OrderPO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id",nullable = false,columnDefinition = "int(11) comment '订单id'")
    private Integer orderId;
    @Column(name = "mobile",nullable = false,columnDefinition = "varchar(11) comment '手机号'")
    private String mobile;
    @Column(name = "customer_id",nullable = false,columnDefinition = "varchar(32) comment '用户id'")
    private String customerId;
    @Column(name = "customer",nullable = false,columnDefinition = "varchar(45) comment '顾客'")
    private String customer;
    @Column(name = "total_price",nullable = false,columnDefinition = "decimal(6,2) comment '总价价格'")
    private BigDecimal totalPrice;
    @Column(name = "pay_price",nullable = false,columnDefinition = "decimal(6,2) comment '实付价格'")
    private BigDecimal payPrice;
    @Column(name = "product_count",nullable = false,columnDefinition = "int(11) comment '订单中商品数量'")
    private Integer productCount;
    @Column(name = "discount",nullable = false,columnDefinition = "decimal(6,2) comment '折扣'")
    private BigDecimal discount;
    @CreatedDate
    @Column(name = "create_time",nullable = false,columnDefinition = "datetime default current_timestamp")
    private LocalDateTime createTime;
    @LastModifiedDate
    @Column(name = "update_time",nullable = false,columnDefinition = "datetime default current_timestamp on update current_timestamp")
    private LocalDateTime updateTime;


}
