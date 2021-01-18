package com.zjl.spring_boot_shiro.dao.impl;

import com.zjl.spring_boot_shiro.dao.PermissionDao;
import com.zjl.spring_boot_shiro.dao.repository.PermissionRepository;
import com.zjl.spring_boot_shiro.domian.bo.PermissionBO;
import com.zjl.spring_boot_shiro.model.PermissionPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @name: PermissionDaoImpl
 * @description:
 * @author: zhou
 * @create: 2020-10-07 19:11
 */
@Repository
public class PermissionDaoImpl implements PermissionDao {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<PermissionPO> getPermissionListByIds(List<Long> permissionList) {
        return permissionRepository.findAllById(permissionList);
    }

    @Override
    public PermissionPO addPermission(PermissionBO permissionBO) {
        PermissionPO permissionPO = new PermissionPO();
        permissionPO.setPermissionName(permissionBO.getPermissionName());
        permissionPO.setPermissionDecs(permissionBO.getPermissionDescription());
        return permissionRepository.save(permissionPO);
    }
}
