package com.github.zhoujiale.spring.boot.security.service.impl;

import com.github.zhoujiale.spring.boot.security.dao.UserDao;
import com.github.zhoujiale.spring.boot.security.model.PermissionPO;
import com.github.zhoujiale.spring.boot.security.model.RolePO;
import com.github.zhoujiale.spring.boot.security.model.SecurityUserPO;
import com.github.zhoujiale.spring.boot.security.model.SelfUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhou
 * @version 1.0
 * @className SelfUserDetailService
 * @description
 * @date 2021/01/18 19:36
 **/
@Slf4j
public class SelfUserDetailService extends CachingUserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private SpringCacheBasedUserCache userCache;

    public SelfUserDetailService(UserDetailsService delegate) {
        super(delegate);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDetails userFromCache = userCache.getUserFromCache(userName);
        if (null != userFromCache){
            return userFromCache;
        }
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
        Set<PermissionPO> permissionPOSet = new HashSet<>(userDao.getAllPermission(rolePOSet.stream()
                .map(RolePO::getRoleId).collect(Collectors.toList())));
        userPO.setAllPermissions(permissionPOSet);
        Collection<? extends GrantedAuthority> authorities = userPO.getAuthorities();
        User user = new User(userName, userPO.getPassword(), authorities);
        SelfUser selfUser = new SelfUser(user);
        userCache.putUserInCache(selfUser);
        return user;
    }
}
