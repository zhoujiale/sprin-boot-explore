package com.zjl.spring_boot_shiro.service.impl;

import com.zjl.spring_boot_shiro.dao.ShiroUserDao;
import com.zjl.spring_boot_shiro.domian.UserParam;
import com.zjl.spring_boot_shiro.model.ShiroUserPO;
import com.zjl.spring_boot_shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @name: UserServiceImpl
 * @description:
 * @author: zhou
 * @create: 2020-10-08 10:47
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ShiroUserDao shiroUserDao;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ShiroUserPO addUser(UserParam userParam) {
        return null;
    }
}
