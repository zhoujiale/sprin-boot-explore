package com.zjl.spring_boot_security.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author zhou
 * @version 1.0
 * @className UserRolePO
 * @description 用户角色
 * @date 2021/01/18 17:54
 **/
@Data
@Entity
@Table(name = "tb_user_role")
@org.hibernate.annotations.Table(appliesTo = "tb_user_role",comment = "用户角色表")
public class UserRolePO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id",nullable = false,columnDefinition = "bigint(20) unsigned comment '关系id'")
    private Long userRoleId;
    @Column(name = "user_id",nullable = false,columnDefinition = "bigint(20) unsigned comment '用户id'")
    private Long userId;
    @Column(name = "role_id",nullable = false,columnDefinition = "bigint(20) unsigned comment '角色id'")
    private Long roleId;
}
