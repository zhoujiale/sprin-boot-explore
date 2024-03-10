package com.github.zhoujiale.spring.boot.mongodb.repository;

import com.github.zhoujiale.spring.boot.mongodb.model.ProductPO;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author zhou
 * @className ProductRepository
 * @date 2021/12/21 11:51
 * @description
 **/
public interface ProductRepository extends MongoRepository<ProductPO,String> {
}
