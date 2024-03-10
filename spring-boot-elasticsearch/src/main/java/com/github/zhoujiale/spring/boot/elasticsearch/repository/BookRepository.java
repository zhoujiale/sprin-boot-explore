package com.github.zhoujiale.spring.boot.elasticsearch.repository;

import com.github.zhoujiale.spring.boot.elasticsearch.model.BookPO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhou
 * @className BookRepository
 * @descrption TODO
 * @date 2022/4/13 13:26
 */
@Repository
public interface BookRepository extends ElasticsearchRepository<BookPO,String> {
}
