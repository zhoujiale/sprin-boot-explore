package com.github.zhoujiale.spring.boot.shiro.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @name: UserRolePO
 * @description: 用户角色
 * @author: zhou
 * @create: 2020-10-07 19:01
 */
@Data
@Entity
@Table(name = "tb_user_role")
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
