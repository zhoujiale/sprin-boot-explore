package com.github.zhoujiale.spring.boot.redis.service;

import com.github.zhoujiale.spring.boot.redis.domain.BookVO;
import com.github.zhoujiale.spring.boot.redis.entity.BookPO;

/**
 * @author zhou
 * @className BookService
 * @descrption 书籍服务
 * @date 2022/4/19 14:48
 */
public interface BookService {

    /**
     * @description 添加书本
     * @date 2022/4/19 15:25
     * @author zhou
     * @param bookPO
     * @return BookPO
     */
    BookPO add(BookPO bookPO);

    /**
     * @description 更新书本
     * @date 2022/4/19 15:30
     * @author zhou
     * @param id
     * @param bookPO
     * @return void
     */
    BookVO update(Long id, BookPO bookPO);

    /**
     * @description 获取书本
     * @date 2022/4/19 15:31
     * @author zhou
     * @param id
     * @return com.github.zhoujiale.spring_boot_redis.domain.BookVO
     */
    BookVO get(Long id);

    /**
     * @description 删除书本
     * @date 2022/4/19 15:32
     * @author zhou
     * @param id
     * @return void
     */
    void delete(Long id);
}
