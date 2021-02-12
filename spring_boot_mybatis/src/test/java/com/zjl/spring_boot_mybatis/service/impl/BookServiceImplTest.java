package com.zjl.spring_boot_mybatis.service.impl;

import com.zjl.commons.util.date.DateUtil;
import com.zjl.spring_boot_mybatis.model.BookPO;
import com.zjl.spring_boot_mybatis.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    void addBooks() {
        List<BookPO> bookPOList = new ArrayList<>();
        BookPO bookPO1 = new BookPO();
        bookPO1.setBookName("三国演义");
        bookPO1.setPrice(new BigDecimal(30));
        bookPO1.setPublicationDate(DateUtil.localDateOf("2019-11-09"));
        bookPOList.add(bookPO1);
        BookPO bookPO2 = new BookPO();
        bookPO2.setBookName("西游记");
        bookPO2.setPrice(new BigDecimal(32.5));
        bookPO2.setPublicationDate(DateUtil.localDateOf("2018-08-23"));
        bookPOList.add(bookPO2);
        bookService.addBooks(bookPOList);
    }

    @Test
    void queryList() {
        List<BookPO> bookPOList = bookService.queryList();
        bookPOList.forEach(bookPO -> System.out.println(bookPO.getBookName()));
    }

    @Test
    void queryByName() {
        BookPO queryByName = bookService.queryByName("西游记");
        Assertions.assertNotEquals(null,queryByName);
    }
}