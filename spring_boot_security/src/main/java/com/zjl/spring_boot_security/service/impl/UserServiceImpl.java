package com.zjl.spring_boot_security.service.impl;

import com.zjl.spring_boot_security.dao.UserDao;
import com.zjl.spring_boot_security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhou
 * @version 1.0
 * @className UserServiceImpl
 * @description
 * @date 2021/01/21 17:46
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
}
