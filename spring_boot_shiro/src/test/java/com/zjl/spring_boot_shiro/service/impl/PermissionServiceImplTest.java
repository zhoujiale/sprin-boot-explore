package com.zjl.spring_boot_shiro.service.impl;

import com.zjl.spring_boot_shiro.domian.bo.PermissionBO;
import com.zjl.spring_boot_shiro.model.PermissionPO;
import com.zjl.spring_boot_shiro.service.PermissionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionServiceImplTest {

    @Autowired
    private PermissionService permissionService;

    @Test
    public void addPermission() {
        PermissionBO permissionBO = new PermissionBO();
        permissionBO.setPermissionDescription("查看3");
        permissionBO.setPermissionName("test3");
        PermissionPO permissionPO = permissionService.addPermission(permissionBO);
        Assert.assertNotEquals(null,permissionPO);
        System.out.println(permissionPO.toString());
    }
}