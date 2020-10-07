package com.zjl.spring_boot_shiro.dao;

import com.zjl.spring_boot_shiro.model.PermissionPO;

import java.util.List;

/**
 * @Auther: zhou
 * @Date: 2020-10-07 19:10
 * @Description:
 */
public interface PermissionDao {

    List<PermissionPO> getPermissionListByIds(List<Long> permissionList);
}
