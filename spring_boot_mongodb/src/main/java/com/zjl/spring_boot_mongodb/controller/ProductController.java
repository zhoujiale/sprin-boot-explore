package com.zjl.spring_boot_mongodb.controller;

import com.zjl.commons.util.response.WebResponse;
import com.zjl.spring_boot_mongodb.model.ProductPO;
import com.zjl.spring_boot_mongodb.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhou
 * @version 1.0
 * @className ProductController
 * @description
 * @date 2021/12/21 11:55
 **/
@Api(value = "商品模块",tags = {"商品模块"})
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "商品添加")
    @PostMapping("/add")
    public WebResponse add(@RequestBody ProductPO productPO){
        productService.addProduct(productPO);
        return WebResponse.success();
    }

    @ApiOperation(value = "获取一个")
    @GetMapping(value = "/getOne")
    public WebResponse getOne(@RequestParam(value = "id")String id){
        ProductPO productPO = productService.getOne(id);
        return WebResponse.success(productPO);
    }
}
