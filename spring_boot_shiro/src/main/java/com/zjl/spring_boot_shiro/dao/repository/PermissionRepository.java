package com.zjl.spring_boot_shiro.dao.repository;

import com.zjl.spring_boot_shiro.model.PermissionPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhou
 * @Date: 2020-10-07 19:11
 * @Description:
 */
public interface PermissionRepository extends JpaRepository<PermissionPO,Long> {
}
