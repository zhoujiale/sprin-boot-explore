package com.zjl.spring_boot_mybatis.service;

/**
 * @author zhou
 * @className LockService
 * @descrption 锁测试服务
 * @date 2022/3/29 12:00
 */
public interface LockService {

    void modify(int count,long id) throws InterruptedException;
}
