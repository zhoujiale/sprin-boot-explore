package com.github.zhoujiale.spring.boot.security.dao.impl;

import com.github.zhoujiale.spring.boot.security.dao.RoleDao;
import com.github.zhoujiale.spring.boot.security.dao.repository.PermissionRepository;
import com.github.zhoujiale.spring.boot.security.dao.repository.RolePermissionRepository;
import com.github.zhoujiale.spring.boot.security.dao.repository.RoleRepository;
import com.github.zhoujiale.spring.boot.security.model.PermissionPO;
import com.github.zhoujiale.spring.boot.security.model.RolePO;
import com.github.zhoujiale.spring.boot.security.model.RolePermissionPO;
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
