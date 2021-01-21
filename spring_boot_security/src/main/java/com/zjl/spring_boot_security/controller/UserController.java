package com.zjl.spring_boot_security.controller;

import com.zjl.commons.util.response.WebResponse;
import com.zjl.spring_boot_security.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhou
 * @version 1.0
 * @className UserController
 * @description
 * @date 2021/01/21 17:44
 **/
@Slf4j
@Api(value = "用户模块",tags = {"用户模块"})
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public WebResponse login(){
        return WebResponse.success();
    }
}
