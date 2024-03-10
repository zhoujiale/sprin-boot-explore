package com.github.zhoujiale.spring.boot.quartz.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.DependsOn;

/**
 * @author zhou
 * @version 1.0
 * @className SpringContextTest
 * @description
 * @date 2021/04/19 18:57
 **/

@DependsOn("springContextUtil")
@SpringBootTest
public class SpringContextTest {

    @Test
    public void test(){
        Object selfJobService = SpringContextUtil.getBean("selfJobService");
        System.out.println(selfJobService.toString());
    }
}
