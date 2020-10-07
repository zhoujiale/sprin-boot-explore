package com.zjl.commons.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @name: WebResponse
 * @description:
 * @author: zhou
 * @create: 2020-10-07 15:15
 */
@Data
@ApiModel(value = "统一响应")
public class WebResponse<T>{

    @ApiModelProperty(value = "响应码")
    private String code;
    @ApiModelProperty(value = "响应信息")
    private String msg;
    @ApiModelProperty(value = "数据")
    private T data;

    public static <T> WebResponse<T> success(T data){
        WebResponse<T> webResponse = new WebResponse<T>();
        webResponse.setCode("200");
        webResponse.setMsg("success");
        webResponse.setData(data);
        return webResponse;
    }

    public static <T> WebResponse<T> success(){
        WebResponse<T> webResponse = new WebResponse<T>();
        webResponse.setCode("200");
        webResponse.setMsg("success");
        return webResponse;
    }
    
    public static <T> WebResponse<T> fail(String code,String msg){
        WebResponse<T> webResponse = new WebResponse<T>();
        webResponse.setMsg(msg);
        webResponse.setCode(code);
        return webResponse;
    }
}
