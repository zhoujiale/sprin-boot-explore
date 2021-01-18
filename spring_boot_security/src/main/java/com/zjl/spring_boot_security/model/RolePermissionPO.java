package com.zjl.spring_boot_security.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author zhou
 * @version 1.0
 * @className RolePermissionPO
 * @description
 * @date 2021/01/18 18:09
 **/
@Data
@Entity
@Table(name = "tb_role_permission")
@org.hibernate.annotations.Table(appliesTo = "tb_role_permission",comment = "角色权限关系表")
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
