package com.zjl.spring_boot_security.dao.impl;

import com.zjl.spring_boot_security.dao.RoleDao;
import com.zjl.spring_boot_security.dao.repository.RoleRepository;
import com.zjl.spring_boot_security.model.RolePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<RolePO> queryRoleListByIds(List<Long> roleIdList) {
        return roleRepository.findAllById(roleIdList);
    }
}
