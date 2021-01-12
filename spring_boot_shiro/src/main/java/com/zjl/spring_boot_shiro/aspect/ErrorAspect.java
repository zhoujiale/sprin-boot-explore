package com.zjl.spring_boot_shiro.aspect;

import com.zjl.commons.util.log.ErrorLogUtil;
import com.zjl.commons.util.response.WebResponse;
import com.zjl.spring_boot_shiro.error.ServiceErrorEnum;
import com.zjl.spring_boot_shiro.error.ServiceErrorException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @name: ErrorAspect
 * @description: 业务异常捕获
 * @author: zhou
 * @create: 2020-10-11 18:24
 */
@ResponseBody
@ControllerAdvice
public class ErrorAspect {

    @ExceptionHandler(ServiceErrorException.class)
    public WebResponse serviceErrHandler(ServiceErrorException exception){
        return WebResponse.fail(exception.getErrCode(),exception.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public WebResponse authenticationErrorHandler(AuthenticationException exception){
        ErrorLogUtil.errorLog(exception);
        return WebResponse.fail(ServiceErrorEnum.AUTH_ERROR.getErrCode(),ServiceErrorEnum.AUTH_ERROR.getErrMsg());
    }

    @ExceptionHandler(AuthorizationException.class)
    public WebResponse authorizationErrorHandler(AuthorizationException exception){
        ErrorLogUtil.errorLog(exception);
        return WebResponse.fail(ServiceErrorEnum.NEED_PERMISSION.getErrCode(),ServiceErrorEnum.NEED_PERMISSION.getErrMsg());
    }
}
