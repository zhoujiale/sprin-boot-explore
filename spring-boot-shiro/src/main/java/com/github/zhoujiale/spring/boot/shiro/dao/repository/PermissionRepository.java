package com.github.zhoujiale.spring.boot.shiro.dao.repository;

import com.github.zhoujiale.spring.boot.shiro.model.PermissionPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhou
 * @Date: 2020-10-07 19:11
 * @Description:
 */
public interface PermissionRepository extends JpaRepository<PermissionPO,Long> {
}
