package com.zjl.spring_boot_shiro.controller;

import com.zjl.commons.util.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: UserController
 * @description: 用户模块
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

    @ApiOperation(value = "退出登录")
    @GetMapping(value = "/logout")
    public WebResponse logout(){
        return WebResponse.success("退出登录");
    }

    @ApiOperation(value = "受保护资源")
    @GetMapping(value = "/needLogin")
    public WebResponse needLogin(){
        return WebResponse.success();
    }

    @ApiOperation(value = "拥有角色")
    @GetMapping(value = "/hasRole")
    public WebResponse validHasRole(){
        return WebResponse.success();
    }

    @ApiOperation(value = "没有角色")
    @GetMapping(value = "/notHasRole")
    public WebResponse validNotHasRole(){
        return WebResponse.success();
    }

    @ApiOperation(value = "拥有权限")
    @GetMapping(value = "/hasPermission")
    public WebResponse validHasPermission(){
        return WebResponse.success();
    }

    @ApiOperation(value = "没有权限")
    @GetMapping(value = "/notHasPermission")
    public WebResponse validNotHasPermission(){
        return WebResponse.success();
    }
}
