package com.zjl.spring_boot_security.model;

import com.zjl.spring_boot_security.config.SelfGrantedAuthority;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author zhou
 * @version 1.0
 * @className SecurityUserPO
 * @description
 * @date 2021/01/18 12:55
 **/
@Data
@Entity
@Table(name = "tb_security_user")
@org.hibernate.annotations.Table(appliesTo = "tb_security_user",comment = "spring_security用户表")
@EntityListeners(AuditingEntityListener.class)
public class SecurityUserPO implements UserDetails,Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",nullable = false,columnDefinition = "bigint(20) unsigned comment '用户id'")
    private Long userId;
    @Column(name = "user_name",nullable = false,columnDefinition = "varchar(45) comment '用户名'")
    private String userName;
    @Column(name = "user_password",nullable = false,columnDefinition = "varchar(128) comment '用户密码'")
    private String userPassword;
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

    @Transient
    private Set<RolePO> allRoles;
    @Transient
    private Set<PermissionPO> allPermissions;
    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    private static final String ROLE_PREFIX = "ROLE_";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        allRoles.forEach(rolePO ->
                        grantedAuthorityList.add(new SelfGrantedAuthority(ROLE_PREFIX + rolePO.getRoleName())));
        allPermissions.forEach(permissionPO ->
                grantedAuthorityList.add(new SelfGrantedAuthority(permissionPO.getPermissionName())));
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
