package com.zjl.spring_boot_mongodb.service.impl;

import com.zjl.spring_boot_mongodb.model.ProductPO;
import com.zjl.spring_boot_mongodb.repository.ProductRepository;
import com.zjl.spring_boot_mongodb.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author zhou
 * @version 1.0
 * @className ProductServiceImpl
 * @description
 * @date 2021/12/21 11:57
 **/
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addProduct(ProductPO productPO) {
        productPO.setCreateDate(LocalDateTime.now());
        productPO.setUpdateDate(LocalDateTime.now());
        productRepository.save(productPO);
        log.info(productPO.getId());
    }

    @Override
    public ProductPO getOne(String id) {
        return productRepository.findById(id).orElse(null);
    }
}
