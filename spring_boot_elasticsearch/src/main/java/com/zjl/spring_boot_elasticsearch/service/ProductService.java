package com.zjl.spring_boot_elasticsearch.service;

import com.zjl.spring_boot_elasticsearch.model.ProductPO;

/**
 * @Auther: zhou
 * @Date: 2021-12-11 19:06
 * @Description:
 */
public interface ProductService {

    /**
     * @description 添加商品
     * @author zhou
     * @created  2021/12/12 16:08
     * @param productPO 商品
     * @return void
     **/
    void add(ProductPO productPO);
    
    /** 
     * @description 通过id删除 
     * @author zhou       
     * @created  2021/12/12 16:40
     * @param 
     * @return void
     **/
    void delete(String productId);
    
    /** 
     * @description 编辑商品 
     * @author zhou       
     * @created  2021/12/12 16:52
     * @param 
     * @return void
     **/
    void modify(ProductPO productPO);
}
