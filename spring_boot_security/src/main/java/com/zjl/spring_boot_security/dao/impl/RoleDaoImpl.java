package com.zjl.spring_boot_security.dao.impl;

import com.zjl.spring_boot_security.dao.RoleDao;
import com.zjl.spring_boot_security.dao.repository.PermissionRepository;
import com.zjl.spring_boot_security.dao.repository.RolePermissionRepository;
import com.zjl.spring_boot_security.dao.repository.RoleRepository;
import com.zjl.spring_boot_security.model.PermissionPO;
import com.zjl.spring_boot_security.model.RolePO;
import com.zjl.spring_boot_security.model.RolePermissionPO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhou
 * @version 1.0
 * @className RoleDaoImpl
 * @description
 * @date 2021/01/18 20:26
 **/
@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<RolePO> queryRoleListByIds(List<Long> roleIdList) {
        return roleRepository.findAllById(roleIdList);
    }

    @Override
    public List<PermissionPO> queryPermissionListByRoleList(List<Long> roleIdList) {
        List<RolePermissionPO> rolePermissionPOList = rolePermissionRepository.findAllByRoleIdIn(roleIdList);
        if (CollectionUtils.isEmpty(rolePermissionPOList)){
            return Collections.emptyList();
        }
        List<Long> permissionIdList = rolePermissionPOList.stream()
                .map(RolePermissionPO::getPermissionId)
                .distinct()
                .collect(Collectors.toList());
        return permissionRepository.findAllById(permissionIdList);
    }
}
