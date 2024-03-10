package com.github.zhoujiale.spring.boot.shiro.service.impl;

import com.github.zhoujiale.spring.boot.shiro.dao.PermissionDao;
import com.github.zhoujiale.spring.boot.shiro.domian.bo.PermissionBO;
import com.github.zhoujiale.spring.boot.shiro.model.PermissionPO;
import com.github.zhoujiale.spring.boot.shiro.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhou
 * @version 1.0
 * @className PermissionServiceImpl
 * @description
 * @date 2021/01/18 18:34
 **/
@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public PermissionPO addPermission(PermissionBO permissionBO) {
        return permissionDao.addPermission(permissionBO);
    }
}
