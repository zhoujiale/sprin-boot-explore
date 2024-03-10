package com.github.zhoujiale.spring.boot.jpa.service.impl;

import com.github.zhoujiale.commons.util.page.PageVO;
import com.github.zhoujiale.spring.boot.jpa.dao.BookDao;
import com.github.zhoujiale.spring.boot.jpa.model.BookPO;
import com.github.zhoujiale.spring.boot.jpa.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @name: BookServiceImpl
 * @description:
 * @author: zhou
 * @create: 2021-02-17 15:23
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public int addBookList(List<BookPO> bookPOList) {
        return bookDao.addBatchBookList(bookPOList);
    }

    @Override
    public List<BookPO> getList(String name) {
        return bookDao.getList(name);
    }

    @Override
    public PageVO<BookPO> getPageByOrder(Integer pageNum,Integer pageSize) {
        Page<BookPO> bookPOPage = bookDao.getPageByOrder(pageNum,pageSize);
        return PageVO.pageOf(bookPOPage.getContent(),pageNum,pageSize,bookPOPage.getTotalElements());
    }

    @Override
    public List<BookPO> queryDynamicBook(String bookName, BigDecimal minPrice, BigDecimal maxPrice) {
        return bookDao.queryBookList(bookName, minPrice, maxPrice);
    }
}
