package com.github.zhoujiale.spring.boot.security.dao.repository;

import com.github.zhoujiale.spring.boot.security.model.SecurityUserPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhou
 * @className UserRepository
 * @date 2021/01/18 19:39
 * @description
 **/
public interface UserRepository extends JpaRepository<SecurityUserPO,Long> {
}
