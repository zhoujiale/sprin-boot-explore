package com.zjl.spring_boot_jpa.dao.impl;

import com.zjl.spring_boot_jpa.dao.BookDao;
import com.zjl.spring_boot_jpa.dao.repository.BookRepository;
import com.zjl.spring_boot_jpa.model.BookPO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @name: BookDaoImpl
 * @description:
 * @author: zhou
 * @create: 2021-02-12 17:31
 */
@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public int addBatchBookList(List<BookPO> bookPOList) {
        List<BookPO> bookPOS = bookRepository.saveAll(bookPOList);
        return bookPOS.size();
    }

    @Override
    public List<BookPO> getList(String name) {
        BookPO bookPO = new BookPO();
        bookPO.setBookName(name);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("bookName", ExampleMatcher.GenericPropertyMatchers.startsWith());
        return bookRepository.findAll(Example.of(bookPO,exampleMatcher));
    }

    @Override
    public Page<BookPO> getPageByOrder(Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.DESC, "publicationDate"));
        return bookRepository.findAll(pageRequest);
    }


    @Override
    public List<BookPO> queryBookList(String bookName, BigDecimal minPrice, BigDecimal maxPrice) {
        Specification<BookPO> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> andList = new ArrayList<>();
            if (StringUtils.isNotBlank(bookName)){
                andList.add(criteriaBuilder.like(root.get("bookName"),"%"+bookName+"%"));
            }
            if (null != minPrice){
                andList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"),minPrice));
            }
            if (null != maxPrice){
                andList.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"),maxPrice));
            }
            return criteriaBuilder.and(andList.toArray(new Predicate[andList.size()]));
        };
        return bookRepository.findAll(specification);
    }
}
