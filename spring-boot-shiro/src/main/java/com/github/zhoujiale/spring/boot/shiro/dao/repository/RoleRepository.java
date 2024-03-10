package com.github.zhoujiale.spring.boot.shiro.dao.repository;

import com.github.zhoujiale.spring.boot.shiro.model.RolePO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhou
 * @Date: 2020-10-07 19:08
 * @Description:
 */
public interface RoleRepository extends JpaRepository<RolePO,Long> {
}
