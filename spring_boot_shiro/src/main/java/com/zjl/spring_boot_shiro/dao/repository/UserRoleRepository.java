package com.zjl.spring_boot_shiro.dao.repository;

import com.zjl.spring_boot_shiro.model.UserRolePO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: zhou
 * @Date: 2020-10-07 19:10
 * @Description:
 */
public interface UserRoleRepository extends JpaRepository<UserRolePO,Long> {

    List<UserRolePO> findAllByUserId(Long userId);
}
