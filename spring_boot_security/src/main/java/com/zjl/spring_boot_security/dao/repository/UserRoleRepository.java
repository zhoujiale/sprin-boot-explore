package com.zjl.spring_boot_security.dao.repository;

import com.zjl.spring_boot_security.model.UserRolePO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhou
 * @className UserRoleRepository
 * @date 2021/01/18 20:22
 * @description
 **/
public interface UserRoleRepository extends JpaRepository<UserRolePO,Long> {
}
