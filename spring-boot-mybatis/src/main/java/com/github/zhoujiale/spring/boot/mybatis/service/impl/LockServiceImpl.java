package com.github.zhoujiale.spring.boot.mybatis.service.impl;

import com.github.zhoujiale.spring.boot.mybatis.mapper.BookPODynamicSqlSupport;
import com.github.zhoujiale.spring.boot.mybatis.mapper.BookPOMapper;
import com.github.zhoujiale.spring.boot.mybatis.service.LockService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.render.MyBatis3RenderingStrategy;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.locks.ReentrantLock;

import static org.mybatis.dynamic.sql.SqlBuilder.*;


/**
 * @author zhou
 * @className LockServiceImpl
 * @descrption 锁服务
 * @date 2022/3/29 12:00
 */
@Slf4j
@Service
public class LockServiceImpl implements LockService {

    @Resource
    private BookPOMapper bookPOMapper;

    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(int count, long id) throws InterruptedException {
        System.out.println("执行前置");
        this.testLock(count, id);
    }

    private synchronized void testLock(int count,long id) throws InterruptedException {
        Thread.sleep(500);
        System.out.println("开始执行");
        UpdateStatementProvider updateStatement = update(SqlTable.of("tb_book"))
                .set(BookPODynamicSqlSupport.bookNumber).equalTo(add(BookPODynamicSqlSupport.bookNumber,constant("1")))
                .where(BookPODynamicSqlSupport.id,isEqualTo(id)).build().render(new MyBatis3RenderingStrategy());
        bookPOMapper.update(updateStatement);
    }
}
