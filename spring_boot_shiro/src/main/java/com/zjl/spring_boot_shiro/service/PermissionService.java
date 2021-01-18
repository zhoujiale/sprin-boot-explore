package com.zjl.spring_boot_shiro.service;

import com.zjl.spring_boot_shiro.domian.bo.PermissionBO;
import com.zjl.spring_boot_shiro.model.PermissionPO;

/**
 * @author zhou
 * @className PermissionService
 * @date 2021/01/18 18:34
 * @description
 **/
public interface PermissionService {

    PermissionPO addPermission(PermissionBO permissionBO);
}
