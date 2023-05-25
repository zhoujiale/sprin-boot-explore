package com.zjl.spring_boot_statemachine.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @classname: Order
 * @author: zhou
 * @description:
 * @date: 2023/5/23 16:50
 */
@Data
@Entity
@Table(name = "tb_order")
@org.hibernate.annotations.Table(appliesTo = "tb_order",comment = "订单")
@EntityListeners(AuditingEntityListener.class)
public class Order implements Serializable {

    @Id
    @Column(name = "order_id",nullable = false,columnDefinition = "varchar(32) comment '订单id'")
    private String orderId;
    @Column(name = "order_status",nullable = false,columnDefinition = "int(11) comment '订单状态'")
    private Integer orderStatus;
    @CreatedDate
    @Column(name = "create_time",nullable = false,columnDefinition = "datetime default current_timestamp")
    private LocalDateTime createTime;
    @LastModifiedDate
    @Column(name = "update_time",nullable = false,columnDefinition = "datetime default current_timestamp on update current_timestamp")
    private LocalDateTime updateTime;
}
