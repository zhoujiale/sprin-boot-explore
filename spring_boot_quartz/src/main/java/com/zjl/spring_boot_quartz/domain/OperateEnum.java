package com.zjl.spring_boot_quartz.domain;

import com.zjl.spring_boot_quartz.error.ServiceErrorEnum;
import com.zjl.spring_boot_quartz.error.ServiceErrorException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author zhou
 * @className OperateEnum
 * @date 2021/03/24 16:34
 * @description
 **/
@Slf4j
@Getter
public enum OperateEnum {

    /**添加**/
    ADD,
    /**修改**/
    MODIFY,
    /**查询**/
    SELECT,
    /**删除**/
    DELETE
    ;

    OperateEnum(){

    }

    public static void validEnum(OperateEnum operateEnum){
        OperateEnum[] values = OperateEnum.values();
        boolean anyMatch = Arrays.stream(values).anyMatch(value -> operateEnum.equals(value));
        if (!anyMatch){
            log.error("非法的操作类型,[{}]",operateEnum.toString());
            throw new ServiceErrorException(ServiceErrorEnum.OPERATE_VALID);
        }
    }
}
