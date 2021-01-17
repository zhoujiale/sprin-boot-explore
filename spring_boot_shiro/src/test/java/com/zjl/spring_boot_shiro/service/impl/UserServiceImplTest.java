package com.zjl.spring_boot_shiro.service.impl;

import com.zjl.spring_boot_shiro.domian.UserParam;
import com.zjl.spring_boot_shiro.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void addUser() {
        UserParam userParam = new UserParam();
        userParam.setUserName("zhou01");
        userParam.setUserPassword("1234qwer");
        List<Long> roleIdList = new ArrayList<>();
        roleIdList.add(2L);
        userParam.setRoleIdList(roleIdList);
        userService.addUser(userParam);
    }
}