package com.github.zhoujiale.spring.boot.security.dao.repository;

import com.github.zhoujiale.spring.boot.security.model.PermissionPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhou
 * @className PermissionRepository
 * @date 2021/02/01 14:12
 * @description
 **/
public interface PermissionRepository extends JpaRepository<PermissionPO,Long> {
}
