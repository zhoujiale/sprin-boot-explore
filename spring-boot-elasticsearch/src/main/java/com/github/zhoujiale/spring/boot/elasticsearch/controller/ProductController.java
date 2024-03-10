package com.github.zhoujiale.spring.boot.elasticsearch.controller;

import com.github.zhoujiale.commons.util.page.PageVO;
import com.github.zhoujiale.commons.util.response.WebResponse;
import com.github.zhoujiale.spring.boot.elasticsearch.model.ProductPO;
import com.github.zhoujiale.spring.boot.elasticsearch.model.ProductQuery;
import com.github.zhoujiale.spring.boot.elasticsearch.model.ProductVO;
import com.github.zhoujiale.spring.boot.elasticsearch.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @name: ProductController
 * @description: 商品api接口
 * @author: zhou
 * @create: 2021-12-11 19:05
 */
@Api(value = "商品api",tags = {"商品api"})
@Slf4j
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    public ProductService productService;

    @ApiOperation(value = "添加")
    @PostMapping(value = "/add")
    public WebResponse add(@RequestBody ProductPO productPO){
        productService.add(productPO);
        return WebResponse.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/{productId}")
    public WebResponse delete(@PathVariable("productId")String productId){
        productService.delete(productId);
        return WebResponse.success();
    }

    @ApiOperation(value = "编辑")
    @PutMapping(value = "/modify")
    public WebResponse modify(@RequestBody ProductPO productPO){
        productService.modify(productPO);
        return WebResponse.success();
    }

    @ApiOperation(value = "查询")
    @GetMapping(value = "/query")
    public WebResponse query(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        ProductQuery productQuery = new ProductQuery();
        productQuery.setPageNum(pageNum);
        productQuery.setPageSize(pageSize);
        PageVO<ProductVO> pageVO = productService.query(productQuery);
        return WebResponse.success(pageVO);
    }
}
