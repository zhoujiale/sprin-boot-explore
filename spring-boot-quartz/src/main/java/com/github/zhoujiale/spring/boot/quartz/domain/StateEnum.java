package com.github.zhoujiale.spring.boot.quartz.domain;

import lombok.Getter;

/**
 * @author zhou
 * @className StateEnum
 * @description 状态
 * @date 2021/04/19 13/57
 **/
@Getter
public enum StateEnum {
    /**运行中**/
    RUN(1),
    /**暂停**/
    PAUSE(2),

    ;
    private Integer code;

    StateEnum(Integer code) {
        this.code = code;
    }
}
