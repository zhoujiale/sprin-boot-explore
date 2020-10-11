package com.zjl.spring_boot_shiro.error;

import lombok.Getter;

/**
 * @Auther: zhou
 * @Date: 2020-10-11 18:20
 * @Description: 从5000开始
 */
@Getter
public enum ServiceErrorEnum {

    ;
    private String errCode;

    private String errMsg;

    ServiceErrorEnum(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
