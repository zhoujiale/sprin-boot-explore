package com.zjl.spring_boot_security.dao.repository;

import com.zjl.spring_boot_security.model.PermissionPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhou
 * @className PermissionRepository
 * @date 2021/02/01 14:12
 * @description
 **/
public interface PermissionRepository extends JpaRepository<PermissionPO,Long> {
}
