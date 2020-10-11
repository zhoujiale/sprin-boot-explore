package com.zjl.spring_boot_shiro.domian;

import com.zjl.spring_boot_shiro.model.PermissionPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @name: PermissionVO
 * @description:
 * @author: zhou
 * @create: 2020-10-11 18:40
 */
@Data
@ApiModel(value = "权限")
public class PermissionVO {
    @ApiModelProperty(value = "权限id")
    private Long permissionId;
    @ApiModelProperty(value = "权限名称")
    private String permissionName;
    @ApiModelProperty(value = "权限描述")
    private String permissionDesc;

    public static PermissionVO covertByPermissionPO(PermissionPO permissionPO) {
        PermissionVO permissionVO = new PermissionVO();
        permissionVO.setPermissionId(permissionPO.getPermissionId());
        permissionVO.setPermissionName(permissionPO.getPermissionName());
        permissionVO.setPermissionDesc(permissionPO.getPermissionDecs());
        return permissionVO;
    }
}
