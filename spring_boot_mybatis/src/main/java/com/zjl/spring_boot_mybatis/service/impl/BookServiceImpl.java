package com.zjl.spring_boot_mybatis.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zjl.commons.util.page.PageVO;
import com.zjl.spring_boot_mybatis.mapper.BookPODynamicSqlSupport;
import com.zjl.spring_boot_mybatis.mapper.BookPOMapper;
import com.zjl.spring_boot_mybatis.model.BookPO;
import com.zjl.spring_boot_mybatis.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @name: BookServiceImpl
 * @description:
 * @author: zhou
 * @create: 2021-02-12 11:51
 */
@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookPOMapper bookPOMapper;

    @Override
    public int addBook(BookPO bookPO) {
        int i = bookPOMapper.insertSelective(bookPO);
        log.info("bookId:[]", bookPO.getBookId());
        return i;
    }

    @Override
    public void addBooks(List<BookPO> bookPOList) {
        bookPOList.forEach(bookPO -> bookPOMapper.insertSelective(bookPO));
    }

    @Override
    public int update(BookPO bookPO) {
        return bookPOMapper.updateByPrimaryKeySelective(bookPO);
    }

    @Override
    public List<BookPO> queryList(BookPO bookPO) {
        return bookPOMapper.select(selectModelQueryExpressionDSL ->
                selectModelQueryExpressionDSL.where(BookPODynamicSqlSupport.bookName, isLike(bookPO.getBookName()))
        );
    }

    @Override
    public BookPO queryByName(String name) {
        return bookPOMapper.selectOne(
                book -> book.where(BookPODynamicSqlSupport.bookName, isEqualTo(name)))
                .orElse(null);
    }

    @Override
    public int updatePrice(BigDecimal price, String bookName) {
        return bookPOMapper.update(updateModelUpdateDSL -> updateModelUpdateDSL
                .set(BookPODynamicSqlSupport.price).equalTo(price)
                .where(BookPODynamicSqlSupport.bookName, isEqualTo(bookName)));
    }

    @Override
    public List<BookPO> orderByTime() {
        return bookPOMapper.select(selectModelQueryExpressionDSL ->
                selectModelQueryExpressionDSL.orderBy(BookPODynamicSqlSupport.publicationDate.descending(),
                        BookPODynamicSqlSupport.createTime.descending()));
    }

    @Override
    public PageVO<BookPO> getPage(Integer pageNum, Integer pageSize) {
        Page<BookPO> page = PageHelper.startPage(pageNum, pageSize).doSelectPage(() ->
                bookPOMapper.select(SelectDSLCompleter.allRows()));
        List<BookPO> result = page.getResult();
        if (CollectionUtils.isNotEmpty(result)) {
            return PageVO.pageOf(result, pageNum, pageSize, page.getTotal());
        } else {
            return PageVO.pageOf(Collections.emptyList(), pageNum, pageSize, 0);
        }
    }

    @Override
    public List<BookPO> groupByPublication(String name) {
        return bookPOMapper.select(selectModelQueryExpressionDSL ->
                selectModelQueryExpressionDSL.where(BookPODynamicSqlSupport.bookName, isEqualTo(name))
                        .groupBy(BookPODynamicSqlSupport.publicationDate));
    }
}
