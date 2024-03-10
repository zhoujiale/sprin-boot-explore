package com.github.zhoujiale.spring.boot.mongodb.service.impl;

import com.github.zhoujiale.commons.util.page.PageVO;
import com.github.zhoujiale.spring.boot.mongodb.model.ProductPO;
import com.github.zhoujiale.spring.boot.mongodb.repository.ProductRepository;
import com.github.zhoujiale.spring.boot.mongodb.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Override
    public void update(ProductPO productPO) {
        productRepository.save(productPO);
    }

    @Override
    public PageVO<ProductPO> queryPage(Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        Page<ProductPO> all = productRepository.findAll(pageRequest);
        return PageVO.pageOf(all.getContent(),pageNum,pageSize,all.getTotalElements());
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }
}
