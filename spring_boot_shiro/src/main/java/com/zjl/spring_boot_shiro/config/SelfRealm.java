package com.zjl.spring_boot_shiro.config;

import com.zjl.spring_boot_shiro.dao.RoleDao;
import com.zjl.spring_boot_shiro.dao.ShiroUserDao;
import com.zjl.spring_boot_shiro.model.PermissionPO;
import com.zjl.spring_boot_shiro.model.RolePO;
import com.zjl.spring_boot_shiro.model.ShiroUserPO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @name: SelfRealm
 * @description: 认证和授权的具体实现
 * @author: zhou
 * @create: 2020-10-07 18:03
 */
@Slf4j
public class SelfRealm extends AuthorizingRealm {

    @Autowired
    private ShiroUserDao shiroUserDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = authenticationToken.getPrincipal().toString();
        ShiroUserPO user = shiroUserDao.getUserByName(principal);
        if (null == user){
            throw new UnknownAccountException();
        }
        if (user.getLocked()){
            throw new LockedAccountException();
        }
        // SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.getUserPassword(),
        //         ByteSource.Util.bytes(user.getSalt()),getName());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.getUserPassword(),getName());
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ShiroUserPO shiroUserPO = (ShiroUserPO) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<RolePO> rolePOList = shiroUserDao.getUserRoleByName(shiroUserPO.getUserName());
        Set<String> roleSet = rolePOList.stream().map(RolePO::getRoleName).collect(Collectors.toSet());
        simpleAuthorizationInfo.setRoles(roleSet);
        if (CollectionUtils.isEmpty(rolePOList)){
            return simpleAuthorizationInfo;
        }
        List<PermissionPO> permissionPOList = roleDao.getPermissionByRoleIdS(rolePOList.stream()
                .map(RolePO::getRoleId).collect(Collectors.toList()));
        Set<String> permissionSet = permissionPOList.stream().map(PermissionPO::getPermissionName).collect(Collectors.toSet());
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }


}
