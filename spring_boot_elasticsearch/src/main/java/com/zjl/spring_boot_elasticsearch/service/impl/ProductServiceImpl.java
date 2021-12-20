package com.zjl.spring_boot_elasticsearch.service.impl;

import com.zjl.commons.util.page.PageVO;
import com.zjl.spring_boot_elasticsearch.model.ProductPO;
import com.zjl.spring_boot_elasticsearch.model.ProductQuery;
import com.zjl.spring_boot_elasticsearch.model.ProductVO;
import com.zjl.spring_boot_elasticsearch.repository.ProductRepository;
import com.zjl.spring_boot_elasticsearch.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @name: ProductServiceImpl
 * @description:
 * @author: zhou
 * @create: 2021-12-11 19:07
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void add(ProductPO productPO) {
        productPO.setCreateDate(LocalDateTime.now());
        productPO.setUpdateDate(LocalDateTime.now());
        productPO = productRepository.save(productPO);
    }

    @Override
    public void delete(String productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public void modify(ProductPO productPO) {
        Optional<ProductPO> byId = productRepository.findById(productPO.getProductId());
        if (!byId.isPresent()){
            log.warn("数据不存在");
            return;
        }
        ProductPO entity = byId.get();
        if (StringUtils.isNotBlank(productPO.getDescription())){
            entity.setDescription(productPO.getDescription());
        }
        if (StringUtils.isNotBlank(productPO.getProductName())){
            entity.setProductName(productPO.getProductName());
        }
        if (null != productPO.getPrice()){
            entity.setPrice(productPO.getPrice());
        }
        entity.setUpdateDate(LocalDateTime.now());
        productRepository.save(entity);
    }

    @Override
    public PageVO<ProductVO> query(ProductQuery productQuery) {
        /**
         * 使用ElasticSearchRestTemplate进行查询
         *
         *
         *
         * **/
        //声明请求
        SearchRequest searchRequest = new SearchRequest("product");
        //构建查询
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        return null;
    }
}
