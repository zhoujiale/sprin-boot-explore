package com.zjl.spring_boot_shiro.dao.repository;

import com.zjl.spring_boot_shiro.model.ShiroUserPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhou
 * @Date: 2020-10-07 17:59
 * @Description:
 */
public interface ShiroUserRepository extends JpaRepository<ShiroUserPO,Long> {
}
