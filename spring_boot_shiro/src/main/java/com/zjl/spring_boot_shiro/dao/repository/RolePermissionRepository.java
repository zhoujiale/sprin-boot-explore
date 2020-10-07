package com.zjl.spring_boot_shiro.dao.repository;

import com.zjl.spring_boot_shiro.model.RolePermissionPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhou
 * @Date: 2020-10-07 19:09
 * @Description:
 */
public interface RolePermissionRepository extends JpaRepository<RolePermissionPO,Long> {
}
