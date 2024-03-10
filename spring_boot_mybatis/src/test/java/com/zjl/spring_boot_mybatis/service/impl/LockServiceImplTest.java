package com.zjl.spring_boot_mybatis.service.impl;

import com.zjl.spring_boot_mybatis.service.LockService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author zhou
 * @className LockServiceImplTest
 * @descrption TODO
 * @date 2022/3/29 12:18
 */

@SpringBootTest
class LockServiceImplTest {

    @Resource
    private LockService lockService;

    @Test
    void modify() {
        int i = 0;
        while (true) {

            while (i < 5) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            lockService.modify(1, 1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, "线程-" + i);
                thread.start();
                i++;
            }
        }
    }

}