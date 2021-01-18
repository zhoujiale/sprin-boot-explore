package com.zjl.spring_boot_security.dao;

import com.zjl.spring_boot_security.model.PermissionPO;
import com.zjl.spring_boot_security.model.RolePO;
import com.zjl.spring_boot_security.model.SecurityUserPO;

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
     * @return com.zjl.spring_boot_security.model.SecurityUserPO
     **/
    SecurityUserPO queryUserByName(String userName);

    /**
     * @description 获取账号所有角色
     * @author zhou
     * @create 2021/1/18 20:19
     * @param userId 账号id
     * @return java.util.List<com.zjl.spring_boot_security.model.RolePO>
     **/
    List<RolePO> getAllRoles(Long userId);


    List<PermissionPO> getAllPermission(List<Long> singletonList);
}
