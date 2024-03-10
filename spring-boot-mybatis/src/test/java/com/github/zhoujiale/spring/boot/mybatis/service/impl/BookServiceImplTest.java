package com.github.zhoujiale.spring.boot.mybatis.service.impl;

import com.github.zhoujiale.commons.util.date.DateUtil;
import com.github.zhoujiale.commons.util.page.PageVO;
import com.github.zhoujiale.spring.boot.mybatis.model.BookPO;
import com.github.zhoujiale.spring.boot.mybatis.service.BookService;
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
        bookPO1.setBookName("骆驼祥子");
        bookPO1.setPrice(new BigDecimal(40));
        bookPO1.setPublicationDate(DateUtil.localDateOf("2019-11-09"));
        bookPOList.add(bookPO1);
        BookPO bookPO2 = new BookPO();
        bookPO2.setBookName("骆驼祥子");
        bookPO2.setPrice(new BigDecimal(32.5));
        bookPO2.setPublicationDate(DateUtil.localDateOf("2018-08-23"));
        bookPOList.add(bookPO2);
        bookService.addBooks(bookPOList);
    }

    @Test
    void queryList() {
        List<BookPO> bookPOList = bookService.queryList(new BookPO());
        bookPOList.forEach(bookPO -> System.out.println(bookPO.getBookName()));
    }

    @Test
    void queryByName() {
        BookPO queryByName = bookService.queryByName("西游记");
        Assertions.assertNotEquals(null,queryByName);
    }

    @Test
    void addBook() {
        BookPO bookPO = new BookPO();
        bookPO.setPublicationDate(DateUtil.localDateOf("2001-03-21"));
        bookPO.setPrice(new BigDecimal(40.31));
        bookPO.setBookName("老人与海");
        int book = bookService.addBook(bookPO);
        Assertions.assertEquals(1,book);
    }

    @Test
    void update() {
        BookPO bookPO = new BookPO();
        bookPO.setBookId(1);
        bookPO.setPrice(new BigDecimal(50));
        bookService.update(bookPO);
    }

    @Test
    void updatePrice() {
        bookService.updatePrice(new BigDecimal(100),"老人与海");
        BookPO bookPO = bookService.queryByName("老人与海");
        Assertions.assertEquals(new BigDecimal(100).setScale(2),bookPO.getPrice());
    }

    @Test
    void orderByTime() {
        List<BookPO> bookPOList = bookService.orderByTime();
        bookPOList.forEach(bookPO -> System.out.println(bookPO.toString()));
    }

    @Test
    void getPage() {
        PageVO<BookPO> poPageVO = bookService.getPage(1,2);
        List<BookPO> list = poPageVO.getList();
        list.forEach(l-> System.out.println(l.toString()));
    }

    @Test
    void groupByPublication() {
        List<BookPO> bookPOList = bookService.groupByPublication("骆驼祥子");
        bookPOList.forEach(bookPO -> System.out.println(bookPO.toString()));
    }
}