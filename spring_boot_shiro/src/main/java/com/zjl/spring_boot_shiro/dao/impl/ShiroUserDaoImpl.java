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

import java.util.Collections;
import java.util.List;
import java.util.Set;
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
        List<UserRolePO> userRolePOList = userRoleRepository.findAllByUserId(byUserName.getUserId());
        if (CollectionUtils.isEmpty(userRolePOList)){
            return Collections.emptyList();
        }
        List<Long> roleList = userRolePOList.stream().map(UserRolePO::getRoleId).collect(Collectors.toList());
        List<RolePO> roleByIds = roleDao.getRoleByIds(roleList);
        return roleByIds;
    }
}
