package com.zjl.spring_boot_jpa.dao.repository;

import com.zjl.spring_boot_jpa.model.BookPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhou
 * @Date: 2021-02-12 17:31
 * @Description:
 */
public interface BookRepository extends JpaRepository<BookPO,Integer> {
}
