package com.github.zhoujiale.commons.util.log;

import lombok.extern.slf4j.Slf4j;

/**
 * @name: ErrorLogUtil
 * @description:
 * @author: zhou
 * @create: 2020-10-07 20:31
 */
@Slf4j
public class ErrorLogUtil {

    public static void errorLog(Exception e){
        log.error("错误类型:{}",e.getClass());
        log.error("错误信息:{}",e.getMessage());
        log.error("错误堆栈:{}", (Object) e.getStackTrace());
    }
}
