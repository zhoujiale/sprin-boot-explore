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
 * @name: OrderDetailPO
 * @description:
 * @author: zhou
 * @create: 2021-02-12 15:40
 */
@Data
@Entity
@Table(name = "tb_order_detail")
@org.hibernate.annotations.Table(appliesTo = "tb_order_detail",comment = "订单详情表")
@EntityListeners(AuditingEntityListener.class)
public class OrderDetailPO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id",nullable = false,columnDefinition = "int(11) comment '订单详情id'")
    private Integer orderDetailId;
    @Column(name = "order_id",nullable = false,columnDefinition = "int(11) comment '订单id'")
    private Integer orderId;
    @Column(name = "book_id",nullable = false,columnDefinition = "int(11) comment '书籍id'")
    private Integer bookId;
    @Column(name = "book_count",nullable = false,columnDefinition = "int(11) comment '书籍数量'")
    private Integer bookCount;
    @Column(name = "unit_price",nullable = false,columnDefinition = "decimal(6,2) comment '单价'")
    private BigDecimal unitPrice;
    @CreatedDate
    @Column(name = "create_time",nullable = false,columnDefinition = "datetime default current_timestamp")
    private LocalDateTime createTime;
    @LastModifiedDate
    @Column(name = "update_time",nullable = false,columnDefinition = "datetime default current_timestamp on update current_timestamp")
    private LocalDateTime updateTime;
}
