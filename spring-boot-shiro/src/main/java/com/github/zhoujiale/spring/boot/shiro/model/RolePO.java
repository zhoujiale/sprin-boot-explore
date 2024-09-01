package com.github.zhoujiale.spring.boot.shiro.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @name: RolePO
 * @description: 角色
 * @author: zhou
 * @create: 2020-10-07 18:33
 */
@Data
@Entity
@Table(name = "tb_role")
@EntityListeners(AuditingEntityListener.class)
public class RolePO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id",nullable = false,columnDefinition = "bigint(20) unsigned comment '角色id'")
    private Long roleId;
    @Column(name = "role_name",nullable = false,columnDefinition = "varchar(45) comment '角色名称'")
    private String roleName;
    @Column(name = "role_desc",nullable = false,columnDefinition = "varchar(128) comment '角色描述'")
    private String roleDesc;
    @CreatedDate
    @Column(name = "create_time",nullable = false,columnDefinition = "datetime default current_timestamp comment '创建时间'")
    private LocalDateTime createTime;
    @LastModifiedDate
    @Column(name = "update_time",nullable = false,columnDefinition = "datetime default current_timestamp on update current_timestamp comment '更新时间'")
    private LocalDateTime updateTime;
}
