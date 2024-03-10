package com.github.zhoujiale.spring.boot.security.aspect;

import com.github.zhoujiale.commons.util.response.WebResponse;
import com.github.zhoujiale.spring.boot.security.error.ServiceErrorException;
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


}
