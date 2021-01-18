package com.zjl.spring_boot_shiro.dao;

import com.zjl.spring_boot_shiro.domian.bo.RoleBO;
import com.zjl.spring_boot_shiro.model.PermissionPO;
import com.zjl.spring_boot_shiro.model.RolePO;

import java.util.List;

/**
 * @Auther: zhou
 * @Date: 2020-10-07 19:07
 * @Description:
 */
public interface RoleDao {

    /**
     * @description 批量获取角色
     * @author zhou
     * @created  2020/10/7 19:57
     * @param
     * @return java.util.List<com.zjl.spring_boot_shiro.model.RolePO>
     **/
    List<RolePO> getRoleByIds(List<Long> idList);

    /**
     * @description 获取角色下的权限
     * @author zhou
     * @created  2020/10/7 20:20
     * @param
     * @return java.util.List<com.zjl.spring_boot_shiro.model.PermissionPO>
     **/
    List<PermissionPO> getPermissionByRoleIdS(List<Long> roleIdList);

    /**
     * @description 添加角色
     * @author zhou
     * @create 2021/1/18 18:21
     * @param roleBO 角色业务
     * @return void
     **/
    RolePO add(RoleBO roleBO);
}
