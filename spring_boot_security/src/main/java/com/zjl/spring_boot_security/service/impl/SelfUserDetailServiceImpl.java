package com.zjl.spring_boot_security.service.impl;

import com.zjl.spring_boot_security.dao.UserDao;
import com.zjl.spring_boot_security.model.RolePO;
import com.zjl.spring_boot_security.model.SecurityUserPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhou
 * @version 1.0
 * @className SelfUserDetailServiceImpl
 * @description
 * @date 2021/01/18 19:36
 **/
@Slf4j
@Component
public class SelfUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SecurityUserPO userPO = userDao.queryUserByName(userName);
        if (null == userPO){
            log.warn("not found user:[{}]",userName);
            throw new UsernameNotFoundException("账号不存在");
        }
        if (!userPO.isAccountNonLocked()){
            log.warn("user has been locked :[{}]",userName);
            throw new LockedException("账号被锁定");
        }
        Set<RolePO> rolePOSet = new HashSet<>(userDao.getAllRoles(userPO.getUserId()));
        userPO.setAllRoles(rolePOSet);
        userDao.getAllPermission(rolePOSet.stream().map(RolePO::getRoleId).collect(Collectors.toList()));
        return null;
    }
}
