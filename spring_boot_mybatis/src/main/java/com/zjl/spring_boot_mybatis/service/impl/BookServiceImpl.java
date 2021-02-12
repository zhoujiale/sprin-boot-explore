package com.zjl.spring_boot_mybatis.service.impl;

import com.zjl.spring_boot_mybatis.mapper.BookPODynamicSqlSupport;
import com.zjl.spring_boot_mybatis.mapper.BookPOMapper;
import com.zjl.spring_boot_mybatis.model.BookPO;
import com.zjl.spring_boot_mybatis.service.BookService;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @name: BookServiceImpl
 * @description:
 * @author: zhou
 * @create: 2021-02-12 11:51
 */
@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookPOMapper bookPOMapper;

    @Override
    public void addBooks(List<BookPO> bookPOList) {
        bookPOList.forEach(bookPO -> bookPOMapper.insertSelective(bookPO));
    }

    @Override
    public List<BookPO> queryList() {
        return bookPOMapper.select(SelectDSLCompleter.allRows());
    }

    @Override
    public BookPO queryByName(String name) {
        return bookPOMapper.selectOne(book->book.where(BookPODynamicSqlSupport.bookName,isEqualTo(name)))
                .orElse(null);
    }
}
