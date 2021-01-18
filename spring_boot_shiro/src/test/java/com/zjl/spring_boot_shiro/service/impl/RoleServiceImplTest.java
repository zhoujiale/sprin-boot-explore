package com.zjl.spring_boot_shiro.service.impl;

import com.zjl.spring_boot_shiro.domian.bo.RoleBO;
import com.zjl.spring_boot_shiro.model.RolePO;
import com.zjl.spring_boot_shiro.service.RoleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void addRole() {
        RoleBO roleBO = new RoleBO();
        roleBO.setRoleName("developer");
        roleBO.setRoleDescription("开发");
        List<Long> permissionIdList = new ArrayList<>();
        permissionIdList.add(1L);
        permissionIdList.add(2L);
        roleBO.setPermissionIdList(permissionIdList);
        RolePO rolePO = roleService.addRole(roleBO);
        Assert.assertNotEquals(null,rolePO);
        System.out.println(rolePO.toString());
    }
}