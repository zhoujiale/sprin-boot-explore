package com.github.zhoujiale.commons.util.valid;

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

    public static boolean validEnum(OperateEnum operateEnum){
        OperateEnum[] values = OperateEnum.values();
        return Arrays.asList(values).contains(operateEnum);
    }
}
