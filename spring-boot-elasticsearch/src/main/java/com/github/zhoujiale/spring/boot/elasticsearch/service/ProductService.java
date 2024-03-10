package com.github.zhoujiale.spring.boot.elasticsearch.service;

import com.github.zhoujiale.commons.util.page.PageVO;
import com.github.zhoujiale.spring.boot.elasticsearch.model.ProductPO;
import com.github.zhoujiale.spring.boot.elasticsearch.model.ProductQuery;
import com.github.zhoujiale.spring.boot.elasticsearch.model.ProductVO;

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

    /**
     * @description 查询商品
     * @author zhou
     * @create 2021/12/20 19:30 
     * @param productQuery
     * @return com.github.zhoujiale.commons.util.page.PageVO<com.github.zhoujiale.spring_boot_elasticsearch.model.ProductVO>
     **/
    PageVO<ProductVO> query(ProductQuery productQuery);

    /**
     * @description 通过java客户端查询
     * @author zhou
     * @create 2021/12/29 21:37
     * @param productQuery
     * @return com.github.zhoujiale.commons.util.page.PageVO<com.github.zhoujiale.spring_boot_elasticsearch.model.ProductVO>
     **/
    PageVO<ProductVO> queryByElasticsearchClient(ProductQuery productQuery);
}
