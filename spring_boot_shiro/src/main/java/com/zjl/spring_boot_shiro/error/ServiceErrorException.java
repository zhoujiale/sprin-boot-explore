package com.zjl.spring_boot_shiro.error;

import lombok.Getter;

/**
 * @name: ServiceErrorException
 * @description: 业务异常
 * @author: zhou
 * @create: 2020-10-11 18:19
 */
@Getter
public class ServiceErrorException extends RuntimeException{

    private String errCode;

    public ServiceErrorException(ServiceErrorEnum serviceErrorEnum){
        super(serviceErrorEnum.getErrMsg());
        this.errCode = serviceErrorEnum.getErrCode();
    }
}
