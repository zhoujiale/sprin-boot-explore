package com.zjl.spring_boot_shiro.dao;

import com.zjl.spring_boot_shiro.domian.bo.PermissionBO;
import com.zjl.spring_boot_shiro.model.PermissionPO;

import java.util.List;

/**
 * @Auther: zhou
 * @Date: 2020-10-07 19:10
 * @Description:
 */
public interface PermissionDao {

    /**
     * @description 获取权限集合
     * @author zhou
     * @create 2021/1/18 18:52
     * @param
     * @return java.util.List<com.zjl.spring_boot_shiro.model.PermissionPO>
     **/
    List<PermissionPO> getPermissionListByIds(List<Long> permissionList);

    /**
     * @description 添加权限
     * @author zhou
     * @create 2021/1/18 18:52
     * @param permissionBO 权限
     * @return com.zjl.spring_boot_shiro.model.PermissionPO
     **/
    PermissionPO addPermission(PermissionBO permissionBO);
}
