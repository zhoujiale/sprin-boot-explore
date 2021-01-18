package com.zjl.spring_boot_security.dao.repository;

import com.zjl.spring_boot_security.model.SecurityUserPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhou
 * @className UserRepository
 * @date 2021/01/18 19:39
 * @description
 **/
public interface UserRepository extends JpaRepository<SecurityUserPO,Long> {
}
