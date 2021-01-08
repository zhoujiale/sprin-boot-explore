package com.zjl.spring_boot_shiro.controller;

import com.zjl.commons.util.response.WebResponse;
import com.zjl.spring_boot_shiro.domian.LoginParam;
import com.zjl.spring_boot_shiro.domian.PasswordParam;
import com.zjl.spring_boot_shiro.domian.UserParam;
import com.zjl.spring_boot_shiro.domian.UserVO;
import com.zjl.spring_boot_shiro.model.ShiroUserPO;
import com.zjl.spring_boot_shiro.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public WebResponse login(@RequestBody LoginParam loginParam){
        UserVO userVO = userService.login(loginParam);
        return WebResponse.success(userVO);
    }

    @ApiOperation(value = "修改密码")
    @PostMapping(value = "/modifyPassword")
    public WebResponse modifyPassword(@RequestBody PasswordParam passwordParam){
        userService.modifyPassword(passwordParam);
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

    @ApiOperation(value = "添加用户")
    @PostMapping(value = "/add")
    public WebResponse addUser(@RequestBody UserParam userParam){
        ShiroUserPO shiroUserPO = userService.addUser(userParam);
        return WebResponse.success(shiroUserPO);
    }
}
