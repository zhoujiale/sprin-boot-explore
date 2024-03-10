package com.github.zhoujiale.spring.boot.security.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhou
 * @version 1.0
 * @className LoginBO
 * @description
 * @date 2021/01/27 15:10
 **/
@Data
@ApiModel(value = "登录业务")
public class LoginBO {
    @ApiModelProperty(value = "账号")
    private String userName;
    @ApiModelProperty(value = "密码")
    private String password;

}
