package com.github.zhoujiale.spring.boot.shiro.domian.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zhou
 * @version 1.0
 * @className RoleBO
 * @description
 * @date 2021/01/18 18:13
 **/
@Data
@ApiModel(value = "角色业务")
public class RoleBO {

    @ApiModelProperty(value = "角色id")
    private Long roleId;
    @ApiModelProperty(value = "角色名")
    private String roleName;
    @ApiModelProperty(value = "角色描述")
    private String roleDescription;
    @ApiModelProperty(value = "权限id集合")
    private List<Long> permissionIdList;
}
