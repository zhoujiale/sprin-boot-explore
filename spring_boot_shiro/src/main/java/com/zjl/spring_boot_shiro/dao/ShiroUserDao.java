package com.zjl.spring_boot_shiro.dao;

import com.zjl.spring_boot_shiro.model.RolePO;
import com.zjl.spring_boot_shiro.model.ShiroUserPO;

import java.util.List;

/**
 * @name: ShiroUserDao
 * @description:
 * @author: zhou
 * @create: 2020-10-07 17:57
 */
public interface ShiroUserDao {
    
    /** 
     * @description 通过名称获取
     * @author zhou       
     * @created  2020/10/7 19:15
     * @param 
     * @return com.zjl.spring_boot_shiro.model.ShiroUserPO
     **/
    ShiroUserPO getUserByName(String name);

    /** 
     * @description 通过名称获取用户的角色 
     * @author zhou       
     * @created  2020/10/7 19:39
     * @param 
     * @return java.util.Set<java.lang.String>
     **/
    List<RolePO> getUserRoleByName(String name);

    /**
     * @description 添加用户
     * @author zhou
     * @created  2020/10/11 17:11
     * @param
     * @return com.zjl.spring_boot_shiro.model.ShiroUserPO
     **/
    ShiroUserPO addUser(ShiroUserPO shiroUserPO);

    /**
     * @description 为用户添加角色
     * @author zhou
     * @created  2020/10/11 17:11
     * @param
     * @return void
     **/
    void addUserRole(Long userId, List<Long> roleIdList);
}
