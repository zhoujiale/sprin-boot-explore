package com.github.zhoujiale.spring.boot.quartz.dao.repository;

import com.github.zhoujiale.spring.boot.quartz.model.SelfJobPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author zhou
 * @className SelfJobRepository
 * @date 2021/03/24 15:44
 * @description
 **/
public interface SelfJobRepository extends JpaRepository<SelfJobPO,Long>, JpaSpecificationExecutor<SelfJobPO> {
}
