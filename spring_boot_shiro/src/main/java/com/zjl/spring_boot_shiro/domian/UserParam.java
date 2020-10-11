package com.zjl.spring_boot_shiro.domian;

import com.zjl.spring_boot_shiro.model.ShiroUserPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @name: UserParam
 * @description: 用户参数
 * @author: zhou
 * @create: 2020-10-08 10:50
 */
@Data
@ApiModel(value = "用户参数")
public class UserParam {

    @ApiModelProperty(value = "用户名称")
    private String userName;
    @ApiModelProperty(value = "用户密码")
    private String userPassword;
    @ApiModelProperty(value = "用户角色")
    private List<Long> roleIdList;

    public static ShiroUserPO covertByUserParam(UserParam userParam){
        ShiroUserPO shiroUserPO = new ShiroUserPO();
        shiroUserPO.setLocked(Boolean.FALSE);
        shiroUserPO.setUserName(userParam.userName);
        shiroUserPO.setUserPassword(userParam.getUserPassword());
        shiroUserPO.setDeptId(1L);
        return shiroUserPO;
    }

}
