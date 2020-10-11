package com.zjl.spring_boot_shiro.service.impl;

import com.zjl.spring_boot_shiro.dao.ShiroUserDao;
import com.zjl.spring_boot_shiro.domian.LoginParam;
import com.zjl.spring_boot_shiro.domian.UserParam;
import com.zjl.spring_boot_shiro.domian.UserVO;
import com.zjl.spring_boot_shiro.model.ShiroUserPO;
import com.zjl.spring_boot_shiro.service.UserService;
import com.zjl.spring_boot_shiro.util.PasswordHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
    @Autowired
    private PasswordHelper passwordHelper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ShiroUserPO addUser(UserParam userParam) {
        ShiroUserPO shiroUserPO = UserParam.covertByUserParam(userParam);
        String salt = passwordHelper.getSalt();
        String encryptPassword = passwordHelper.encryptPassword(shiroUserPO.getUserPassword(), salt);
        shiroUserPO.setUserPassword(encryptPassword);
        shiroUserPO.setSalt(salt);
        shiroUserPO = shiroUserDao.addUser(shiroUserPO);
        shiroUserDao.addUserRole(shiroUserPO.getUserId(),userParam.getRoleIdList());
        return shiroUserPO;
    }

    @Override
    public UserVO login(LoginParam loginParam) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginParam.getUserName(),loginParam.getPassword());
        currentUser.login(token);
        return null;
    }
}
