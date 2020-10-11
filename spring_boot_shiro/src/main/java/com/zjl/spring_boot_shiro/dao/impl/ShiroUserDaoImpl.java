package com.zjl.spring_boot_shiro.dao.impl;

import com.zjl.spring_boot_shiro.dao.RoleDao;
import com.zjl.spring_boot_shiro.dao.ShiroUserDao;
import com.zjl.spring_boot_shiro.dao.repository.ShiroUserRepository;
import com.zjl.spring_boot_shiro.dao.repository.UserRoleRepository;
import com.zjl.spring_boot_shiro.model.RolePO;
import com.zjl.spring_boot_shiro.model.ShiroUserPO;
import com.zjl.spring_boot_shiro.model.UserRolePO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @name: ShiroUserDaoImpl
 * @description:
 * @author: zhou
 * @create: 2020-10-07 18:02
 */
@Repository
public class ShiroUserDaoImpl implements ShiroUserDao {

    @Autowired
    private ShiroUserRepository shiroUserRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleDao roleDao;

    @Override
    public ShiroUserPO getUserByName(String name) {
        return shiroUserRepository.findByUserName(name);
    }

    @Override
    public List<RolePO> getUserRoleByName(String name) {
        ShiroUserPO byUserName = shiroUserRepository.findByUserName(name);
        if (null == byUserName){
            return Collections.emptyList();
        }
        return getUserRoleByUserId(byUserName.getUserId());
    }

    @Override
    public ShiroUserPO addUser(ShiroUserPO shiroUserPO) {
        return shiroUserRepository.save(shiroUserPO);
    }

    @Override
    public void addUserRole(Long userId, List<Long> roleIdList) {
        UserRolePO userRolePO;
        List<UserRolePO> userRolePOList = new ArrayList<>(roleIdList.size());
        for (Long role : roleIdList) {
            userRolePO = new UserRolePO();
            userRolePO.setRoleId(role);
            userRolePO.setUserId(userId);
            userRolePOList.add(userRolePO);
        }
        userRoleRepository.saveAll(userRolePOList);
    }

    @Override
    public List<RolePO> getUserRoleByUserId(Long userId) {
        List<UserRolePO> userRolePOList = userRoleRepository.findAllByUserId(userId);
        if (CollectionUtils.isEmpty(userRolePOList)){
            return Collections.emptyList();
        }
        List<Long> roleList = userRolePOList.stream().map(UserRolePO::getRoleId).collect(Collectors.toList());
        List<RolePO> roleByIds = roleDao.getRoleByIds(roleList);
        return roleByIds;
    }


}
