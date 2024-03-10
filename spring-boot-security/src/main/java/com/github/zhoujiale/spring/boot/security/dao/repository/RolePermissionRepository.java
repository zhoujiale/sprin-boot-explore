package com.github.zhoujiale.spring.boot.security.dao.repository;

import com.github.zhoujiale.spring.boot.security.model.RolePermissionPO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhou
 * @className RolePermissionRepository
 * @date 2021/02/01 14:07
 * @description
 **/
public interface RolePermissionRepository extends JpaRepository<RolePermissionPO,Long> {

    /**
     * @description 通过角色id获取权限
     * @author zhou
     * @create 2021/2/1 14:08
     * @param roleIdList 角色定
     * @return java.util.List<com.github.zhoujiale.spring_boot_security.model.RolePermissionPO>
     **/
    List<RolePermissionPO> findAllByRoleIdIn(List<Long> roleIdList);
}
