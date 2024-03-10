package com.github.zhoujiale.spring.boot.security.dao;

import com.github.zhoujiale.spring.boot.security.model.PermissionPO;
import com.github.zhoujiale.spring.boot.security.model.RolePO;
import com.github.zhoujiale.spring.boot.security.model.SecurityUserPO;

import java.util.List;

/**
 * @author zhou
 * @className UserDao
 * @date 2021/01/18 19:38
 * @description
 **/
public interface UserDao {

    /**
     * @description 通过账号查询用户
     * @author zhou
     * @create 2021/1/18 19:53
     * @param userName 账号名
     * @return com.github.zhoujiale.spring_boot_security.model.SecurityUserPO
     **/
    SecurityUserPO queryUserByName(String userName);

    /**
     * @description 获取账号所有角色
     * @author zhou
     * @create 2021/1/18 20:19
     * @param userId 账号id
     * @return java.util.List<com.github.zhoujiale.spring_boot_security.model.RolePO>
     **/
    List<RolePO> getAllRoles(Long userId);

    /**
     * @description 获取账号的所有权限
     * @author zhou
     * @create 2021/2/1 13:43
     * @param roleIdList 角色id
     * @return java.util.List<com.github.zhoujiale.spring_boot_security.model.PermissionPO>
     **/
    List<PermissionPO> getAllPermission(List<Long> roleIdList);

    /**
     * @description 添加账号和账号对应的角色
     * @author zhou
     * @create 2021/2/1 14:16
     * @param securityUserPO 账号
     * @return com.github.zhoujiale.spring_boot_security.model.SecurityUserPO
     **/
    SecurityUserPO addSecurityUser(SecurityUserPO securityUserPO);

    /**
     * @description 为账号添加角色
     * @author zhou
     * @create 2021/2/1 14:21
     * @param userId 账号id
     * @param roleIdList  角色集合
     * @return void
     **/
    void addUserRole(Long userId, List<Long> roleIdList);
}
