package com.github.zhoujiale.spring.boot.mongodb.controller;

import com.github.zhoujiale.commons.util.page.PageVO;
import com.github.zhoujiale.commons.util.response.WebResponse;
import com.github.zhoujiale.spring.boot.mongodb.model.ProductPO;
import com.github.zhoujiale.spring.boot.mongodb.service.ProductService;
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

    @ApiOperation(value = "获取")
    @GetMapping(value = "/getOne")
    public WebResponse getOne(@RequestParam(value = "id")String id){
        ProductPO productPO = productService.getOne(id);
        return WebResponse.success(productPO);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/delete")
    public WebResponse delete(@RequestParam(value = "id")String id){
        productService.delete(id);
        return WebResponse.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping(value = "/update")
    public WebResponse update(@RequestBody ProductPO productPO){
        productService.update(productPO);
        return WebResponse.success();
    }

    @ApiOperation(value = "列表")
    @GetMapping(value = "/list")
    public WebResponse list(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                            @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        PageVO<ProductPO> pageVO = productService.queryPage(pageNum,pageSize);
        return WebResponse.success(pageVO);
    }
}
