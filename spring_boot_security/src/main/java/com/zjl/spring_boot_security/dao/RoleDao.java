package com.zjl.spring_boot_security.dao;

import com.zjl.spring_boot_security.model.RolePO;

import java.util.List;

/**
 * @author zhou
 * @version 1.0
 * @className RoleDao
 * @description
 * @date 2021/01/18 20:26
 **/
public interface RoleDao {
    
    /**
     * @description 通过角色id
     * @author zhou
     * @create 2021/1/18 20:27 
     * @param roleIdList 角色id集合
     * @return java.util.List<com.zjl.spring_boot_security.model.RolePO>
     **/
    List<RolePO> queryRoleListByIds(List<Long> roleIdList);
}
