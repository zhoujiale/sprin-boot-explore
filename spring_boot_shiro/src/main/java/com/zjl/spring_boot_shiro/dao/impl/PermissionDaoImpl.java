package com.zjl.spring_boot_shiro.dao.impl;

import com.zjl.spring_boot_shiro.dao.PermissionDao;
import com.zjl.spring_boot_shiro.dao.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @name: PermissionDaoImpl
 * @description:
 * @author: zhou
 * @create: 2020-10-07 19:11
 */
@Repository
public class PermissionDaoImpl implements PermissionDao {

    @Autowired
    private PermissionRepository permissionRepository;
}
