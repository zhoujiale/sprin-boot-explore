package com.zjl.spring_boot_jpa.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @name: CustomerPO
 * @description:
 * @author: zhou
 * @create: 2021-02-12 15:32
 */
@Data
@Entity
@Table(name = "tb_customer")
@org.hibernate.annotations.Table(appliesTo = "tb_customer",comment = "顾客表")
@EntityListeners(AuditingEntityListener.class)
public class CustomerPO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id",nullable = false,columnDefinition = "int(11) comment '顾客id'")
    private Integer customerId;
    @Column(name = "customer_name",nullable = false,columnDefinition = "varchar(45) comment '顾客名'")
    private String customerName;
    @CreatedDate
    @Column(name = "create_time",nullable = false,columnDefinition = "datetime default current_timestamp")
    private LocalDateTime createTime;
    @LastModifiedDate
    @Column(name = "update_time",nullable = false,columnDefinition = "datetime default current_timestamp on update current_timestamp")
    private LocalDateTime updateTime;
}
