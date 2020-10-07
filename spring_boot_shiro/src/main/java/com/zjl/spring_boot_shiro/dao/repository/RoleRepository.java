package com.zjl.spring_boot_shiro.dao.repository;

import com.zjl.spring_boot_shiro.model.RolePO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhou
 * @Date: 2020-10-07 19:08
 * @Description:
 */
public interface RoleRepository extends JpaRepository<RolePO,Long> {
}
