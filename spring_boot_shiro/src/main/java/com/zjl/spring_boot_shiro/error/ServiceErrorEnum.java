package com.zjl.spring_boot_shiro.error;

import lombok.Getter;

/**
 * @Auther: zhou
 * @Date: 2020-10-11 18:20
 * @Description: 从5000开始
 */
@Getter
public enum ServiceErrorEnum {

    UN_KNOWN_ACCOUNT("5000", "账号不存在"),
    ERROR_CREDENTIALS("5001", "账号或密码错误"),
    LOCK_ACCOUNT("5002", "账号被锁定"),
    AUTH_ERROR("5003", "认证异常"),
    NEED_LOGIN("5004","未登录或登录过期"),
    NEED_PERMISSION("5079","该账号没有这项操作权限"),
    ADD_ROLE_ERROR("5080", "添加角色异常")
    ;
    private String errCode;

    private String errMsg;

    ServiceErrorEnum(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
