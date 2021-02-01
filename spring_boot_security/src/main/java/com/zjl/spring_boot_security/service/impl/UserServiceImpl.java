package com.zjl.spring_boot_security.service.impl;

import com.zjl.spring_boot_security.dao.UserDao;
import com.zjl.spring_boot_security.domain.bo.LoginBO;
import com.zjl.spring_boot_security.domain.bo.UserBO;
import com.zjl.spring_boot_security.model.SecurityUserPO;
import com.zjl.spring_boot_security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void userLogin(LoginBO loginBO) {
        SecurityContext currentUser = SecurityContextHolder.getContext();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginBO.getUserName(),loginBO.getPassword());
        final Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        currentUser.setAuthentication(authenticate);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public SecurityUserPO addUser(UserBO userBO) {
        SecurityUserPO securityUserPO = UserBO.securityUserOf(userBO);
        String secure = passwordEncoder.encode(userBO.getUserPassword());
        securityUserPO.setUserPassword(secure);
        userDao.addSecurityUser(securityUserPO);
        userDao.addUserRole(securityUserPO.getUserId(),userBO.getRoleIdList());
        return securityUserPO;
    }
}
