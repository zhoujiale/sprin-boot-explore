package com.github.zhoujiale.spring.boot.security.service.impl;


import com.github.zhoujiale.spring.boot.security.domain.bo.UserBO;
import com.github.zhoujiale.spring.boot.security.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void addUser() {
        UserBO userBO = new UserBO();
        userBO.setUserName("user");
        userBO.setUserPassword("123qwe");
        List<Long> roleIdList = new ArrayList<>();
        roleIdList.add(2L);
        userBO.setRoleIdList(roleIdList);
        userService.addUser(userBO);
    }
}
