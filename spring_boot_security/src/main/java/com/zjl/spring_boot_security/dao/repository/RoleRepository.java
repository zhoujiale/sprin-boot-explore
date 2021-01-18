package com.zjl.spring_boot_security.dao.repository;

import com.zjl.spring_boot_security.model.RolePO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhou
 * @className RoleRepository
 * @date 2021/01/18 20:30
 * @description
 **/
public interface RoleRepository extends JpaRepository<RolePO,Long> {
}
