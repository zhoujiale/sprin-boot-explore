package com.zjl.spring_boot_shiro.controller;

import com.zjl.commons.util.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: UserController
 * @description:
 * @author: zhou
 * @create: 2020-10-07 15:00
 */
@Api(value = "用户模块",tags = {"用户模块"})
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public WebResponse login(){
        return WebResponse.success();
    }

    @ApiOperation(value = "修改密码")
    @PostMapping(value = "/modifyPassword")
    public WebResponse modifyPassword(){
        return WebResponse.success();
    }


}
