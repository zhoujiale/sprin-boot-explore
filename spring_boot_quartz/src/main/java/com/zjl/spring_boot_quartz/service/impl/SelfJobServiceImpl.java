package com.zjl.spring_boot_quartz.service.impl;

import com.zjl.spring_boot_quartz.service.SelfJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author zhou
 * @version 1.0
 * @className SelfJobServiceImpl
 * @description
 * @date 2021/04/19 17:14
 **/
@Slf4j
@Service(value = "selfJobService")
public class SelfJobServiceImpl implements SelfJobService {


    @Override
    public void minuteJob() {
      log.info("job:[{}],method:[{}]","selfJobService","minuteJob");
      log.info("执行时间:[{}]", LocalDateTime.now().toString());
    }
}
