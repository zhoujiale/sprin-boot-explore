package com.github.zhoujiale.spring.boot.shiro.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @name: RolePermissionPO
 * @description: 角色权限关系表
 * @author: zhou
 * @create: 2020-10-07 18:56
 */
@Data
@Entity
@Table(name = "tb_role_permission")
public class RolePermissionPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_permission_id",nullable = false,columnDefinition = "bigint(20) unsigned comment '关系id'")
    private Long rolePermissionId;
    @Column(name = "role_id",nullable = false,columnDefinition = "bigint(20) unsigned comment '角色id'")
    private Long roleId;
    @Column(name = "permission_id",nullable = false,columnDefinition = "bigint(20) unsigned comment '权限id'")
    private Long permissionId;
}
