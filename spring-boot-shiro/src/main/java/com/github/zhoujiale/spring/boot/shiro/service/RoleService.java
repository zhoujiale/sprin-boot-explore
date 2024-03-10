package com.github.zhoujiale.spring.boot.shiro.service;

import com.github.zhoujiale.spring.boot.shiro.domian.bo.RoleBO;
import com.github.zhoujiale.spring.boot.shiro.model.RolePO;

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
     * @return com.github.zhoujiale.spring_boot_shiro.model.RolePO
     **/
    RolePO addRole(RoleBO roleBO);
}
