package com.zjl.spring_boot_shiro.service;

import com.zjl.spring_boot_shiro.domian.bo.RoleBO;
import com.zjl.spring_boot_shiro.model.RolePO;

/**
 * @author zhou
 * @className RoleService
 * @date 2021/01/18 18:11
 * @description
 **/
public interface RoleService {

    /**
     * @description 添加角色
     * @author zhou
     * @create 2021/1/18 18:18
     * @param roleBO 角色业务
     * @return com.zjl.spring_boot_shiro.model.RolePO
     **/
    RolePO addRole(RoleBO roleBO);
}
