package com.github.zhoujiale.spring.boot.shiro.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @name: ShiroUserPO
 * @description: 用户
 * @author: zhou
 * @create: 2020-10-05 11:50
 */
@Data
@Entity
@Table(name = "tb_shiro_user")
@EntityListeners(AuditingEntityListener.class)
public class ShiroUserPO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",nullable = false,columnDefinition = "bigint(20) unsigned comment '用户id'")
    private Long userId;
    @Column(name = "user_name",nullable = false,columnDefinition = "varchar(45) comment '用户名'")
    private String userName;
    @Column(name = "user_password",nullable = false,columnDefinition = "varchar(128) comment '用户密码'")
    private String userPassword;
    @Column(name = "salt",columnDefinition = "varchar(128) comment '加盐'")
    private String salt;
    @Column(name = "dept_id",nullable = false,columnDefinition = "bigint(20) comment '部门'")
    private Long deptId;
    @Column(name = "is_locked",nullable = false,columnDefinition = "tinyint(1) default 0 comment '是否被锁'")
    private Boolean locked;
    @CreatedDate
    @Column(name = "create_time",nullable = false,columnDefinition = "datetime default current_timestamp comment '创建时间'")
    private LocalDateTime createTime;
    @LastModifiedDate
    @Column(name = "update_time",nullable = false,columnDefinition = "datetime default current_timestamp on update current_timestamp comment '更新时间'")
    private LocalDateTime updateTime;
}
