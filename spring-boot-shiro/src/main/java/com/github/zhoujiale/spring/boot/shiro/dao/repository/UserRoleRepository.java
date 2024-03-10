package com.github.zhoujiale.spring.boot.shiro.dao.repository;

import com.github.zhoujiale.spring.boot.shiro.model.UserRolePO;
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
