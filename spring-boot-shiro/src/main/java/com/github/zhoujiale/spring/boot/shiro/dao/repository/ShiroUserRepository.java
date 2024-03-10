package com.github.zhoujiale.spring.boot.shiro.dao.repository;

import com.github.zhoujiale.spring.boot.shiro.model.ShiroUserPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhou
 * @Date: 2020-10-07 17:59
 * @Description:
 */
public interface ShiroUserRepository extends JpaRepository<ShiroUserPO,Long> {

    ShiroUserPO findByUserName(String name);
}
