package com.github.zhoujiale.spring.boot.shiro.dao.impl;

import com.github.zhoujiale.spring.boot.shiro.dao.PermissionDao;
import com.github.zhoujiale.spring.boot.shiro.dao.repository.PermissionRepository;
import com.github.zhoujiale.spring.boot.shiro.domian.bo.PermissionBO;
import com.github.zhoujiale.spring.boot.shiro.model.PermissionPO;
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
