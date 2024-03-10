package com.github.zhoujiale.spring.boot.redis.repository;

import com.github.zhoujiale.spring.boot.redis.entity.BookPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zhou
 * @className BookRepository
 * @descrption book
 * @date 2022/4/19 14:51
 */
@Repository
public interface BookRepository extends JpaRepository<BookPO, Long> {

    @Modifying
    @Query(value = "update BookPO set bookName = :#{#bookPO.bookName} where bookId = :#{#bookPO.bookId}")
    int updateBook(@Param("bookPO") BookPO bookPO);
}
