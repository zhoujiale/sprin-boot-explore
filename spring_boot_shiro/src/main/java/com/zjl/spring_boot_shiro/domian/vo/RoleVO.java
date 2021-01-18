package com.zjl.spring_boot_shiro.domian.vo;

import com.zjl.spring_boot_shiro.model.RolePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @name: RoleVO
 * @description:
 * @author: zhou
 * @create: 2020-10-11 18:38
 */
@Data
@ApiModel(value = "角色")
public class RoleVO {

    @ApiModelProperty(value = "角色id")
    private Long roleId;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "角色描述")
    private String roleDesc;

    public static RoleVO covertByRolePO(RolePO rolePO){
        RoleVO roleVO = new RoleVO();
        roleVO.setRoleDesc(rolePO.getRoleDesc());
        roleVO.setRoleId(rolePO.getRoleId());
        roleVO.setRoleName(rolePO.getRoleName());
        return roleVO;
    }
}
