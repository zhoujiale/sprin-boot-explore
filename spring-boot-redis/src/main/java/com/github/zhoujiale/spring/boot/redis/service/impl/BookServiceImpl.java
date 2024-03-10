package com.github.zhoujiale.spring.boot.redis.service.impl;

import com.github.zhoujiale.spring.boot.redis.domain.BookVO;
import com.github.zhoujiale.spring.boot.redis.entity.BookPO;
import com.github.zhoujiale.spring.boot.redis.repository.BookRepository;
import com.github.zhoujiale.spring.boot.redis.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author zhou
 * @className BookServiceImpl
 * @descrption 书籍服务
 * @date 2022/4/19 14:51
 */
@Slf4j
@Service
@CacheConfig(keyGenerator = "selfKeyGenerator",cacheNames = "book")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    @CachePut(key = "#bookPO.bookId")
    public BookPO add(BookPO bookPO) {
        bookPO.setCreateDate(LocalDateTime.now());
        bookPO.setUpdateDate(LocalDateTime.now());
        bookRepository.save(bookPO);
        return bookPO;
    }

    @Override
    @CachePut(key = "#id",unless = "null == #result")
    @Transactional(rollbackFor = Exception.class)
    public BookVO update(Long id, BookPO bookPO) {
        bookPO.setBookId(id);
        int i = bookRepository.updateBook(bookPO);
        if (0 == i){
            return null;
        }
        BookVO bookVO = new BookVO();
        bookVO.setBookId(id);
        bookVO.setBookName(bookPO.getBookName());
        return bookVO;
    }

    @Override
    @Cacheable(key = "#id",unless = "null == #result")
    public BookVO get(Long id) {
        BookPO one = bookRepository.getOne(id);
        if (null == one){
            return null;
        }
        BookVO bookVO = new BookVO();
        bookVO.setBookId(one.getBookId());
        bookVO.setBookName(one.getBookName());
        return bookVO;
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
