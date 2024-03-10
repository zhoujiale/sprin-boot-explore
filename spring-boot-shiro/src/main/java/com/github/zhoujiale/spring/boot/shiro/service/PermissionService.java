package com.github.zhoujiale.spring.boot.shiro.service;

import com.github.zhoujiale.spring.boot.shiro.domian.bo.PermissionBO;
import com.github.zhoujiale.spring.boot.shiro.model.PermissionPO;

/**
 * @author zhou
 * @className PermissionService
 * @date 2021/01/18 18:34
 * @description
 **/
public interface PermissionService {

    PermissionPO addPermission(PermissionBO permissionBO);
}
