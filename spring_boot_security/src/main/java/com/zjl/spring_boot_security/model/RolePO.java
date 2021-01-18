package com.zjl.spring_boot_security.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author zhou
 * @version 1.0
 * @className RolePO
 * @description
 * @date 2021/01/18 17:44
 **/
@Data
@Entity
@Table(name = "tb_role")
@org.hibernate.annotations.Table(appliesTo = "tb_role",comment = "角色表")
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
