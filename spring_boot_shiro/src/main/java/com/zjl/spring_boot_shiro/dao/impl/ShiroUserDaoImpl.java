package com.zjl.spring_boot_shiro.dao.impl;

import com.zjl.spring_boot_shiro.dao.ShiroUserDao;
import com.zjl.spring_boot_shiro.dao.repository.ShiroUserRepository;
import com.zjl.spring_boot_shiro.dao.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @name: ShiroUserDaoImpl
 * @description:
 * @author: zhou
 * @create: 2020-10-07 18:02
 */
@Repository
public class ShiroUserDaoImpl implements ShiroUserDao {

    @Autowired
    private ShiroUserRepository shiroUserRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
}
