package com.github.zhoujiale.spring.boot.security.domain.bo;

import com.github.zhoujiale.spring.boot.security.model.SecurityUserPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zhou
 * @version 1.0
 * @className UserBO
 * @description
 * @date 2021/02/01 13:34
 **/
@Data
@ApiModel(value = "用户业务参数")
public class UserBO {

    @ApiModelProperty(value = "用户名称")
    private String userName;
    @ApiModelProperty(value = "用户密码")
    private String userPassword;
    @ApiModelProperty(value = "用户角色")
    private List<Long> roleIdList;

    public static SecurityUserPO securityUserOf(UserBO userBO){
        SecurityUserPO userPO = new SecurityUserPO();
        userPO.setLocked(Boolean.FALSE);
        userPO.setUserName(userBO.userName);
        userPO.setUserPassword(userBO.userPassword);
        userPO.setDeptId(1L);
        return userPO;
    }
}
