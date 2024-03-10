package com.github.zhoujiale.spring.boot.mongodb.service;

import com.github.zhoujiale.commons.util.page.PageVO;
import com.github.zhoujiale.spring.boot.mongodb.model.ProductPO;

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
     * @return com.github.zhoujiale.spring_boot_mongodb.model.ProductPO
     **/
    ProductPO getOne(String id);

    /**
     * @description 更新
     * @date 2022/4/8 20:51
     * @author zhou
     * @param productPO
     * @return void
     */
    void update(ProductPO productPO);
    
    /**
     * @description 分页查询
     * @date 2022/4/8 20:54
     * @author zhou
     * @param pageNum
     * @param pageSize
     * @return com.github.zhoujiale.commons.util.page.PageVO<com.github.zhoujiale.spring_boot_mongodb.model.ProductPO>
     */
    PageVO<ProductPO> queryPage(Integer pageNum, Integer pageSize);

    /**
     * @description 删除
     * @date 2022/4/8 21:56
     * @author zhou
     * @param id
     * @return void
     */
    void delete(String id);
}
