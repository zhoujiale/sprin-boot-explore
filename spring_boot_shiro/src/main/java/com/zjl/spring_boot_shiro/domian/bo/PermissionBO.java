package com.zjl.spring_boot_shiro.domian.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhou
 * @version 1.0
 * @className PermissionBO
 * @description
 * @date 2021/01/18 18:15
 **/
@Data
@ApiModel(value = "权限业务")
public class PermissionBO {

    @ApiModelProperty(value = "权限id")
    private Long permissionId;
    @ApiModelProperty(value = "权限名称")
    private String permissionName;
    @ApiModelProperty(value = "权限描述")
    private String permissionDescription;


}
