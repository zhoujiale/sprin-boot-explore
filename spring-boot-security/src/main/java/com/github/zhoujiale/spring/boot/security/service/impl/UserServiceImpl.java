package com.github.zhoujiale.spring.boot.security.service.impl;

import com.github.zhoujiale.spring.boot.security.dao.UserDao;
import com.github.zhoujiale.spring.boot.security.domain.bo.LoginBO;
import com.github.zhoujiale.spring.boot.security.domain.bo.UserBO;
import com.github.zhoujiale.spring.boot.security.model.SecurityUserPO;
import com.github.zhoujiale.spring.boot.security.service.UserService;
import com.github.zhoujiale.spring.boot.security.utlil.JWTUtil;
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
    public String userLogin(LoginBO loginBO) {
        SecurityContext currentUser = SecurityContextHolder.getContext();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginBO.getUserName(),loginBO.getPassword());
        final Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        currentUser.setAuthentication(authenticate);
        SecurityUserPO securityUserPO = userDao.queryUserByName(loginBO.getUserName());
        return JWTUtil.getJwtToken(securityUserPO);
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
