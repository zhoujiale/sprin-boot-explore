package com.github.zhoujiale.spring.boot.elasticsearch.repository;

import com.github.zhoujiale.spring.boot.elasticsearch.model.ProductPO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @name: ProductRepository
 * @description:
 * @author: zhou
 * @create: 2021-12-11 19:09
 */
public interface ProductRepository extends ElasticsearchRepository<ProductPO,String> {
}
