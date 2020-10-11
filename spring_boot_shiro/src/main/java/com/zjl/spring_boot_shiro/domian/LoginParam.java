package com.zjl.spring_boot_shiro.domian;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @name: LoginParam
 * @description:
 * @author: zhou
 * @create: 2020-10-11 17:58
 */
@Data
@ApiModel(value = "登录参数")
public class LoginParam {

    private String userName;

    private String password;
}
