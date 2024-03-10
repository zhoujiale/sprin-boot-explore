package com.github.zhoujiale.spring.boot.shiro.domian.bo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @name: PasswordParam
 * @description:
 * @author: zhou
 * @create: 2020-10-13 23:12
 */
@Data
@ApiModel(value = "修改密码参数")
public class PasswordParam {

    private String userName;

    private String password;

    private String newPassword;
}
