package com.zjl.spring_boot_shiro.dao.impl;

import com.zjl.spring_boot_shiro.dao.PermissionDao;
import com.zjl.spring_boot_shiro.dao.RoleDao;
import com.zjl.spring_boot_shiro.dao.repository.RolePermissionRepository;
import com.zjl.spring_boot_shiro.dao.repository.RoleRepository;
import com.zjl.spring_boot_shiro.domian.bo.RoleBO;
import com.zjl.spring_boot_shiro.model.PermissionPO;
import com.zjl.spring_boot_shiro.model.RolePO;
import com.zjl.spring_boot_shiro.model.RolePermissionPO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @name: RoleDaoImpl
 * @description:
 * @author: zhou
 * @create: 2020-10-07 19:07
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<RolePO> getRoleByIds(List<Long> idList) {
        return roleRepository.findAllById(idList);
    }

    @Override
    public List<PermissionPO> getPermissionByRoleIdS(List<Long> roleIdList) {
        if (CollectionUtils.isEmpty(roleIdList)){
            return Collections.emptyList();
        }
        List<RolePermissionPO> all = rolePermissionRepository.findAllByRoleIdIn(roleIdList);
        if (CollectionUtils.isEmpty(all)){
            return Collections.emptyList();
        }
        List<PermissionPO> permissionPOList = permissionDao.getPermissionListByIds(all.stream()
                .map(RolePermissionPO::getPermissionId).collect(Collectors.toList()));
        return permissionPOList;
    }

    @Override
    public RolePO add(RoleBO roleBO) {
        RolePO rolePO = new RolePO();
        rolePO.setRoleName(roleBO.getRoleName());
        rolePO.setRoleDesc(roleBO.getRoleDescription());
        rolePO = roleRepository.save(rolePO);
        List<Long> permissionIdList = roleBO.getPermissionIdList();
        List<RolePermissionPO> rolePermissionPOList = new ArrayList<>(permissionIdList.size());
        RolePermissionPO rolePermissionPO;
        for (Long permissionId : permissionIdList) {
            rolePermissionPO = new RolePermissionPO();
            rolePermissionPO.setRoleId(rolePO.getRoleId());
            rolePermissionPO.setPermissionId(permissionId);
            rolePermissionPOList.add(rolePermissionPO);
        }
        rolePermissionRepository.saveAll(rolePermissionPOList);
        return rolePO;
    }
}
