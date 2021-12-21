package com.zjl.spring_boot_mongodb.service;

import com.zjl.spring_boot_mongodb.model.ProductPO;

/**
 * @author zhou
 * @className ProductService
 * @date 2021/12/21 11:56
 * @description
 **/
public interface ProductService {
    
    /**
     * @description 添加商品
     * @author zhou
     * @create 2021/12/21 12:08 
     * @param 
     * @return void
     **/
    void addProduct(ProductPO productPO);
    
    /**
     * @description 通过id获取商品
     * @author zhou
     * @create 2021/12/21 18:34
     * @param 
     * @return com.zjl.spring_boot_mongodb.model.ProductPO
     **/
    ProductPO getOne(String id);
}
