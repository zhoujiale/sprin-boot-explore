package com.zjl.spring_boot_security.controller;

import com.zjl.commons.util.response.WebResponse;
import com.zjl.spring_boot_security.domain.bo.LoginBO;
import com.zjl.spring_boot_security.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public WebResponse login(@RequestBody LoginBO loginBO){
        userService.userLogin(loginBO);
        return WebResponse.success();
    }
}
