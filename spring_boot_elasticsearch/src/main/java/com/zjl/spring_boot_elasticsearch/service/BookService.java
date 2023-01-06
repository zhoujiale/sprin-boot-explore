package com.zjl.spring_boot_elasticsearch.service;

import com.zjl.spring_boot_elasticsearch.model.BookPO;

import java.util.List;

/**
 * @author zhou
 * @className BookService
 * @descrption 书籍
 * @date 2022/4/13 13:25
 */
public interface BookService {

    /**
     * @description 添加书籍
     * @date 2022/4/13 13:28
     * @author zhou
     * @param bookPO
     * @return void
     */
    void add(BookPO bookPO);

    /**
     * @description 批量新增
     * @date 2022/12/19 15:27
     * @author zhou
     * @param bookPOList
     * @return void
     */
    void batchAddList(List<BookPO> bookPOList);

    /**
     * @description 列表
     * @date 2022/4/13 13:50
     * @author zhou
     * @param labels
     * @return java.util.List<java.awt.print.Book>
     */
    List<BookPO> list(String labels);

    /**
     * @description 更新
     * @date 2022/11/29 17:34
     * @author zhou
     * @param bookPO
     * @return void
     */
    void update(BookPO bookPO);

    /**
     * @description 动态更新
     * @date 2022/12/19 14:33
     * @author zhou
     * @param bookPO
     * @return void
     */
    void updateDynamic(BookPO bookPO);
}
