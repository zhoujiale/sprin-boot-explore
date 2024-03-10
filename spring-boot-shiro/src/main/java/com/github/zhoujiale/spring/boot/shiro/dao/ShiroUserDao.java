package com.github.zhoujiale.spring.boot.shiro.dao;

import com.github.zhoujiale.spring.boot.shiro.model.RolePO;
import com.github.zhoujiale.spring.boot.shiro.model.ShiroUserPO;

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
     * @return com.github.zhoujiale.spring_boot_shiro.model.ShiroUserPO
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
     * @description 通过id获取用户的角色 
     * @author zhou       
     * @created  2020/10/11 19:39
     * @param 
     * @return java.util.List<com.github.zhoujiale.spring_boot_shiro.model.RolePO>
     **/
    List<RolePO> getUserRoleByUserId(Long userId);

    /**
     * @description 添加用户
     * @author zhou
     * @created  2020/10/11 17:11
     * @param
     * @return com.github.zhoujiale.spring_boot_shiro.model.ShiroUserPO
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

    /**
     * @description 修改用户
     * @author zhou
     * @created  2020/10/14 0:01
     * @param
     * @return void
     **/
    void modifyPassword(ShiroUserPO userByName);
}
