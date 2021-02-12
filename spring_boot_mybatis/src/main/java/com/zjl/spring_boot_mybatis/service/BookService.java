package com.zjl.spring_boot_mybatis.service;

import com.zjl.spring_boot_mybatis.model.BookPO;

import java.util.List;

/**
 * @Auther: zhou
 * @Date: 2021-02-12 11:50
 * @Description:
 */
public interface BookService {

    void addBooks(List<BookPO> bookPOList);

    List<BookPO> queryList();

    BookPO queryByName(String name);
}
