package com.zjl.spring_boot_jpa.model;

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
@Table(name = "tb_order")
@org.hibernate.annotations.Table(appliesTo = "tb_order",comment = "订单表")
@EntityListeners(AuditingEntityListener.class)
public class OrderPO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id",nullable = false,columnDefinition = "int(11) comment '订单id'")
    private Integer orderId;
    @Column(name = "customer_id",nullable = false,columnDefinition = "int(11) comment '顾客id'")
    private Integer customerId;
    @Column(name = "price",nullable = false,columnDefinition = "decimal(6,2) comment '价格'")
    private BigDecimal price;
    @CreatedDate
    @Column(name = "create_time",nullable = false,columnDefinition = "datetime default current_timestamp")
    private LocalDateTime createTime;
    @LastModifiedDate
    @Column(name = "update_time",nullable = false,columnDefinition = "datetime default current_timestamp on update current_timestamp")
    private LocalDateTime updateTime;


}
