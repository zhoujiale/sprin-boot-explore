package com.github.zhoujiale.spring.boot.quartz.domain;

import lombok.Getter;

/**
 * @author zhou
 * @className ExecuteEnum
 * @description
 * @date 2021/03/26 13/03
 **/
@Getter
public enum ExecuteEnum {

    SUCCESS(1,"成功"),
    FAIL(0,"失败")
    ;
    private Integer code;
    private String msg;

    ExecuteEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
