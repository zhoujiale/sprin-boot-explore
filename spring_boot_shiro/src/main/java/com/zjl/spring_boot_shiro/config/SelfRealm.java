package com.zjl.spring_boot_shiro.config;

import com.zjl.spring_boot_shiro.dao.ShiroUserDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @name: SelfRealm
 * @description: 认证和授权的具体实现
 * @author: zhou
 * @create: 2020-10-07 18:03
 */
public class SelfRealm {

    @Autowired
    private ShiroUserDao shiroUserDao;


}
