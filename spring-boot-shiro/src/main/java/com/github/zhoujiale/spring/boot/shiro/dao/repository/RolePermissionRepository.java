package com.github.zhoujiale.spring.boot.shiro.dao.repository;

import com.github.zhoujiale.spring.boot.shiro.model.RolePermissionPO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: zhou
 * @Date: 2020-10-07 19:09
 * @Description:
 */
public interface RolePermissionRepository extends JpaRepository<RolePermissionPO,Long> {

    List<RolePermissionPO> findAllByRoleIdIn(List<Long> roleIds);
}
