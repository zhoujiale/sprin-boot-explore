package com.github.zhoujiale.spring.boot.security.dao.repository;

import com.github.zhoujiale.spring.boot.security.model.UserRolePO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhou
 * @className UserRoleRepository
 * @date 2021/01/18 20:22
 * @description
 **/
public interface UserRoleRepository extends JpaRepository<UserRolePO,Long> {
}
