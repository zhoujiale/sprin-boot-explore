package com.github.zhoujiale.spring.boot.shiro.dao;

import com.github.zhoujiale.spring.boot.shiro.domian.bo.PermissionBO;
import com.github.zhoujiale.spring.boot.shiro.model.PermissionPO;

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
     * @return java.util.List<com.github.zhoujiale.spring_boot_shiro.model.PermissionPO>
     **/
    List<PermissionPO> getPermissionListByIds(List<Long> permissionList);

    /**
     * @description 添加权限
     * @author zhou
     * @create 2021/1/18 18:52
     * @param permissionBO 权限
     * @return com.github.zhoujiale.spring_boot_shiro.model.PermissionPO
     **/
    PermissionPO addPermission(PermissionBO permissionBO);
}
