package com.zjl.spring_boot_quartz.dao.impl;

import com.zjl.spring_boot_quartz.dao.SelfJobLogDao;
import com.zjl.spring_boot_quartz.dao.repository.SelfJobLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author zhou
 * @version 1.0
 * @className SelfJobLogDaoImpl
 * @description
 * @date 2021/03/24 15:59
 **/
@Repository
public class SelfJobLogDaoImpl implements SelfJobLogDao {

    @Autowired
    private SelfJobLogRepository selfJobLogRepository;
}
