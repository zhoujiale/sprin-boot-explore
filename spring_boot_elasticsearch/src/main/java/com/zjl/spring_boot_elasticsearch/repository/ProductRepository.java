package com.zjl.spring_boot_elasticsearch.repository;

import com.zjl.spring_boot_elasticsearch.model.ProductPO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @name: ProductRepository
 * @description:
 * @author: zhou
 * @create: 2021-12-11 19:09
 */
public interface ProductRepository extends ElasticsearchRepository<ProductPO,String> {
}
