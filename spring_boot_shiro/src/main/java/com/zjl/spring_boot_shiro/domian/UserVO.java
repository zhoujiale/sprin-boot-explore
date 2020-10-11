package com.zjl.spring_boot_shiro.domian;

import com.zjl.spring_boot_shiro.model.PermissionPO;
import com.zjl.spring_boot_shiro.model.RolePO;
import com.zjl.spring_boot_shiro.model.ShiroUserPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @name: UserVO
 * @description:
 * @author: zhou
 * @create: 2020-10-11 17:14
 */
@Data
@ApiModel(value = "用户信息")
public class UserVO {

    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "部门id")
    private Long deptId;
    @ApiModelProperty(value = "角色集")
    private List<RoleVO> roleVOList;
    @ApiModelProperty(value = "权限集")
    private List<PermissionVO> permissionVOList;

    public static UserVO covertToUserVO(ShiroUserPO userByName, List<RolePO> rolePOList, List<PermissionPO> permissionPOList) {
        UserVO userVO = new UserVO();
        userVO.setUserId(userByName.getUserId());
        userVO.setUserName(userByName.getUserName());
        userVO.setDeptId(userByName.getDeptId());
        if (CollectionUtils.isEmpty(rolePOList)){
            userVO.setRoleVOList(Collections.emptyList());
        }else {
            List<RoleVO> roleVOList = new ArrayList<>(rolePOList.size());
            for (RolePO rolePO : rolePOList) {
                RoleVO roleVO = RoleVO.covertByRolePO(rolePO);
                roleVOList.add(roleVO);
            }
            userVO.setRoleVOList(roleVOList);
        }
        if (CollectionUtils.isEmpty(permissionPOList)){
            userVO.setPermissionVOList(Collections.emptyList());
        }else {
            List<PermissionVO> permissionVOList = new ArrayList<>(permissionPOList.size());
            for (PermissionPO permissionPO : permissionPOList) {
                PermissionVO permissionVO = PermissionVO.covertByPermissionPO(permissionPO);
                permissionVOList.add(permissionVO);
            }
            userVO.setPermissionVOList(permissionVOList);
        }
        return userVO;
    }
}
