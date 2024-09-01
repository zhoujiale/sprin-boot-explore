package com.github.zhoujiale.spring.boot.shiro.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @name: PermissionPO
 * @description: 权限
 * @author: zhou
 * @create: 2020-10-07 18:41
 */
@Data
@Entity
@Table(name = "tb_permission")
@EntityListeners(AuditingEntityListener.class)
public class PermissionPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id",nullable = false,columnDefinition = "bigint(20) unsigned comment '权限id'")
    private Long permissionId;
    @Column(name = "permission_name",nullable = false,columnDefinition = "varchar(45) comment '权限名称'")
    private String permissionName;
    @Column(name = "permission_decs",nullable = false,columnDefinition = "varchar(128) comment '权限描述'")
    private String permissionDecs;
    @CreatedDate
    @Column(name = "create_time",nullable = false,columnDefinition = "datetime default current_timestamp comment '创建时间'")
    private LocalDateTime createTime;
    @LastModifiedDate
    @Column(name = "update_time",nullable = false,columnDefinition = "datetime default current_timestamp on update current_timestamp comment '更新时间'")
    private LocalDateTime updateTime;
}
