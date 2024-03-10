package com.github.zhoujiale.spring.boot.security.dao.impl;

import com.github.zhoujiale.spring.boot.security.dao.RoleDao;
import com.github.zhoujiale.spring.boot.security.dao.UserDao;
import com.github.zhoujiale.spring.boot.security.dao.repository.UserRepository;
import com.github.zhoujiale.spring.boot.security.dao.repository.UserRoleRepository;
import com.github.zhoujiale.spring.boot.security.model.PermissionPO;
import com.github.zhoujiale.spring.boot.security.model.RolePO;
import com.github.zhoujiale.spring.boot.security.model.SecurityUserPO;
import com.github.zhoujiale.spring.boot.security.model.UserRolePO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhou
 * @version 1.0
 * @className UserDaoImpl
 * @description
 * @date 2021/01/18 19:39
 **/
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleDao roleDao;


    @Override
    public SecurityUserPO queryUserByName(String userName) {
        SecurityUserPO securityUserPO = new SecurityUserPO();
        securityUserPO.setUserName(userName);
        Example<SecurityUserPO> userPOExample = Example.of(securityUserPO);
        return userRepository.findOne(userPOExample).orElse(null);
    }

    @Override
    public List<RolePO> getAllRoles(Long userId) {
        UserRolePO userRolePO = new UserRolePO();
        userRolePO.setUserId(userId);;
        Example<UserRolePO> userRolePOExample = Example.of(userRolePO);
        List<UserRolePO> userRolePOList = userRoleRepository.findAll(userRolePOExample);
        if (CollectionUtils.isEmpty(userRolePOList)){
            return Collections.emptyList();
        }
        return roleDao.queryRoleListByIds(userRolePOList.stream().map(UserRolePO::getRoleId).collect(Collectors.toList()));
    }

    @Override
    public List<PermissionPO> getAllPermission(List<Long> roleIdList) {
        return roleDao.queryPermissionListByRoleList(roleIdList);
    }

    @Override
    public SecurityUserPO addSecurityUser(SecurityUserPO securityUserPO) {
        return userRepository.save(securityUserPO);
    }

    @Override
    public void addUserRole(Long userId, List<Long> roleIdList) {
        List<UserRolePO> userRolePOList = new ArrayList<>(roleIdList.size());
        UserRolePO userRolePO;
        for (Long roleId : roleIdList) {
            userRolePO = new UserRolePO();
            userRolePO.setUserId(userId);
            userRolePO.setRoleId(roleId);
            userRolePOList.add(userRolePO);
        }
        userRoleRepository.saveAll(userRolePOList);
    }
}
