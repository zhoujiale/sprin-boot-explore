package com.zjl.spring_boot_security.service;

import com.zjl.spring_boot_security.domain.bo.LoginBO;

/**
 * @author zhou
 * @className UserService
 * @date 2021/01/21 17:46
 * @description
 **/
public interface UserService {

    /**
     * @description 登录
     * @author zhou
     * @create 2021/1/30 19:05
     * @param loginBO 登录业务参数
     * @return void
     **/
    void userLogin(LoginBO loginBO);
}
