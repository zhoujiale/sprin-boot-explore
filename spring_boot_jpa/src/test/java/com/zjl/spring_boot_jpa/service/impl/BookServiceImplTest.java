package com.zjl.spring_boot_jpa.service.impl;

import com.zjl.commons.util.date.DateUtil;
import com.zjl.commons.util.page.PageVO;
import com.zjl.spring_boot_jpa.model.BookPO;
import com.zjl.spring_boot_jpa.service.BookService;
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
    void addBookList() {
        List<BookPO> bookPOList = new ArrayList<>();
        BookPO bookPO1 = new BookPO();
        bookPO1.setBookName("西游记");
        bookPO1.setPrice(new BigDecimal(80));
        bookPO1.setPublicationDate(DateUtil.localDateOf("2019-01-09"));
        bookPOList.add(bookPO1);
        BookPO bookPO2 = new BookPO();
        bookPO2.setBookName("红楼梦");
        bookPO2.setPrice(new BigDecimal(82.50));
        bookPO2.setPublicationDate(DateUtil.localDateOf("2018-05-23"));
        bookPOList.add(bookPO2);
        bookService.addBookList(bookPOList);
    }

    @Test
    void getList() {
        List<BookPO> list = bookService.getList("祥子");
        list.forEach(l-> System.out.println(l.toString()));
    }

    @Test
    void getPageByOrder() {
        PageVO<BookPO> pageByOrder = bookService.getPageByOrder(1, 2);
        pageByOrder.getList()
                .forEach(bookPO -> System.out.println(bookPO.toString()));
    }

    @Test
    void queryDynamicBook() {
        List<BookPO> queryDynamicBook = bookService.queryDynamicBook("骆驼", new BigDecimal(50), new BigDecimal(70));
        queryDynamicBook.forEach(book-> System.out.println(book.toString()));
    }
}