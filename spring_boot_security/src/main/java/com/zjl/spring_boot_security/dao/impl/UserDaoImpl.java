package com.zjl.spring_boot_security.dao.impl;

import com.zjl.spring_boot_security.dao.RoleDao;
import com.zjl.spring_boot_security.dao.UserDao;
import com.zjl.spring_boot_security.dao.repository.UserRepository;
import com.zjl.spring_boot_security.dao.repository.UserRoleRepository;
import com.zjl.spring_boot_security.model.RolePO;
import com.zjl.spring_boot_security.model.SecurityUserPO;
import com.zjl.spring_boot_security.model.UserRolePO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

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
}
