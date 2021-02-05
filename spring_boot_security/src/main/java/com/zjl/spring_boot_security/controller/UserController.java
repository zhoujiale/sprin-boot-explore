package com.zjl.spring_boot_security.controller;

import com.zjl.commons.util.response.WebResponse;
import com.zjl.spring_boot_security.domain.bo.LoginBO;
import com.zjl.spring_boot_security.domain.bo.UserBO;
import com.zjl.spring_boot_security.model.SecurityUserPO;
import com.zjl.spring_boot_security.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhou
 * @version 1.0
 * @className UserController
 * @description
 * @date 2021/01/21 17:44
 **/
@Slf4j
@Api(value = "用户模块", tags = {"用户模块"})
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public WebResponse login(@RequestBody LoginBO loginBO, HttpServletResponse response, HttpServletRequest request) {
        userService.userLogin(loginBO);
        Cookie selfCookie = new Cookie("selfCookie", "my-cookie");
        selfCookie.setPath(request.getContextPath() + "/");
        response.addCookie(selfCookie);
        return WebResponse.success();
    }


    @ApiOperation(value = "修改密码")
    @PostMapping(value = "/modifyPassword")
    public WebResponse modifyPassword() {
        return WebResponse.success();
    }

    @ApiOperation(value = "受保护资源")
    @GetMapping(value = "/needLogin")
    public WebResponse needLogin() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        System.out.println(authentication.isAuthenticated());
        return WebResponse.success("受保护的资源");
    }

    @ApiOperation(value = "拥有角色")
    @GetMapping(value = "/hasRole")
    @PreAuthorize("hasRole('ROLE_USER')")
    public WebResponse validHasRole() {
        return WebResponse.success("拥有user角色");
    }

    @ApiOperation(value = "没有角色")
    @GetMapping(value = "/notHasRole")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public WebResponse validNotHasRole() {
        return WebResponse.success("拥有admin角色");
    }

    @ApiOperation(value = "拥有权限")
    @GetMapping(value = "/hasPermission")
    @PreAuthorize(value = "hasAuthority('TEST_ONE')")
    public WebResponse validHasPermission() {
        return WebResponse.success("拥有test1权限");
    }

    @ApiOperation(value = "没有权限")
    @GetMapping(value = "/notHasPermission")
    @PreAuthorize(value = "hasAuthority('TEST_TWO')")
    public WebResponse validNotHasPermission() {
        return WebResponse.success("拥有test2权限");
    }

    @ApiOperation(value = "添加用户")
    @PostMapping(value = "/add")
    public WebResponse addUser(@RequestBody UserBO userBO) {
        SecurityUserPO securityUserPO = userService.addUser(userBO);
        return WebResponse.success(securityUserPO);
    }
}
