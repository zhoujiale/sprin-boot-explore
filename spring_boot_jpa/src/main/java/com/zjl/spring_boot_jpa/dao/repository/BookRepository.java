package com.zjl.spring_boot_jpa.dao.repository;

import com.zjl.spring_boot_jpa.model.BookPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Auther: zhou
 * @Date: 2021-02-12 17:31
 * @Description:
 */
public interface BookRepository extends JpaRepository<BookPO,Integer>, JpaSpecificationExecutor<BookPO> {

    List<BookPO> findBookPOSByBookNameEquals(String name);
}
