package com.zjl.spring_boot_jpa.dao.impl;

import com.zjl.spring_boot_jpa.dao.BookDao;
import com.zjl.spring_boot_jpa.dao.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
