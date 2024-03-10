package com.github.zhoujiale.spring.boot.shiro.controller;

import com.github.zhoujiale.commons.util.response.WebResponse;
import com.github.zhoujiale.spring.boot.shiro.domian.bo.LoginParam;
import com.github.zhoujiale.spring.boot.shiro.domian.bo.PasswordParam;
import com.github.zhoujiale.spring.boot.shiro.domian.bo.UserParam;
import com.github.zhoujiale.spring.boot.shiro.domian.vo.UserVO;
import com.github.zhoujiale.spring.boot.shiro.model.ShiroUserPO;
import com.github.zhoujiale.spring.boot.shiro.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
        userService.logout();
        return WebResponse.success("退出登录");
    }

    @ApiOperation(value = "受保护资源")
    @GetMapping(value = "/needLogin")
    public WebResponse needLogin(){
        return WebResponse.success("受保护的资源");
    }

    @ApiOperation(value = "拥有角色")
    @GetMapping(value = "/hasRole")
    @RequiresRoles(value = "user")
    public WebResponse validHasRole(){
        return WebResponse.success("拥有user角色");
    }

    @ApiOperation(value = "没有角色")
    @GetMapping(value = "/notHasRole")
    @RequiresRoles(value = "admin")
    public WebResponse validNotHasRole(){
        return WebResponse.success("拥有admin角色");
    }

    @ApiOperation(value = "拥有权限")
    @GetMapping(value = "/hasPermission")
    @RequiresPermissions(value = "test1")
    public WebResponse validHasPermission(){
        return WebResponse.success("拥有test1权限");
    }

    @ApiOperation(value = "没有权限")
    @GetMapping(value = "/notHasPermission")
    @RequiresPermissions(value = "test2")
    public WebResponse validNotHasPermission(){
        return WebResponse.success("拥有test2权限");
    }

    @ApiOperation(value = "添加用户")
    @PostMapping(value = "/add")
    public WebResponse addUser(@RequestBody UserParam userParam){
        ShiroUserPO shiroUserPO = userService.addUser(userParam);
        return WebResponse.success(shiroUserPO);
    }
}
