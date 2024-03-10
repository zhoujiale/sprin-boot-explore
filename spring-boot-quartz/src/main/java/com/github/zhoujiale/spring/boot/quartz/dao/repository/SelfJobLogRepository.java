package com.github.zhoujiale.spring.boot.quartz.dao.repository;

import com.github.zhoujiale.spring.boot.quartz.model.SelfJobLogPO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhou
 * @className SelfJobLogRepository
 * @date 2021/03/24 15:59
 * @description
 **/
public interface SelfJobLogRepository extends JpaRepository<SelfJobLogPO,Long> {

    Integer deleteSelfJobLogPOSByLogIdIn(List<Long> logIdList);
}
