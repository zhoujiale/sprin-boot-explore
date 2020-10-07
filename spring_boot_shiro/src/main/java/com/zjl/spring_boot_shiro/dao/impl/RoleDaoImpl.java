package com.zjl.spring_boot_shiro.dao.impl;

import com.zjl.spring_boot_shiro.dao.RoleDao;
import com.zjl.spring_boot_shiro.dao.repository.RolePermissionRepository;
import com.zjl.spring_boot_shiro.dao.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @name: RoleDaoImpl
 * @description:
 * @author: zhou
 * @create: 2020-10-07 19:07
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;
}
