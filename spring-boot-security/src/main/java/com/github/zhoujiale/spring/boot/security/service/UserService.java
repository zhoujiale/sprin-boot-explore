package com.github.zhoujiale.spring.boot.security.service;

import com.github.zhoujiale.spring.boot.security.domain.bo.LoginBO;
import com.github.zhoujiale.spring.boot.security.domain.bo.UserBO;
import com.github.zhoujiale.spring.boot.security.model.SecurityUserPO;

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
    String userLogin(LoginBO loginBO);

    /**
     * @description 添加用户
     * @author zhou
     * @create 2021/2/1 13:38
     * @param userBO 用户也参数
     * @return com.github.zhoujiale.spring_boot_security.model.SecurityUserPO
     **/
    SecurityUserPO addUser(UserBO userBO);

}
