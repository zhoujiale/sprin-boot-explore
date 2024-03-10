package com.github.zhoujiale.spring.boot.shiro.service.impl;

import com.github.zhoujiale.spring.boot.shiro.domian.bo.RoleBO;
import com.github.zhoujiale.spring.boot.shiro.model.RolePO;
import com.github.zhoujiale.spring.boot.shiro.service.RoleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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