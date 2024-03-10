package com.github.zhoujiale.spring.boot.security.dao.repository;

import com.github.zhoujiale.spring.boot.security.model.RolePO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhou
 * @className RoleRepository
 * @date 2021/01/18 20:30
 * @description
 **/
public interface RoleRepository extends JpaRepository<RolePO,Long> {
}
