package com.github.zhoujiale.spring.boot.elasticsearch.controller;

import com.github.zhoujiale.commons.util.response.WebResponse;
import com.github.zhoujiale.spring.boot.elasticsearch.model.BookPO;
import com.github.zhoujiale.spring.boot.elasticsearch.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhou
 * @className BookController
 * @descrption 商品
 * @date 2022/4/13 13:24
 */
@Api(value = "商品模块",tags = {"商品模块"})
@Slf4j
@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "添加商品")
    @PostMapping(value = "/add")
    public WebResponse add(@RequestBody BookPO bookPO){
        bookService.add(bookPO);
        return WebResponse.success();
    }

    @ApiOperation(value = "批量添加")
    @PostMapping(value = "/batchAdd")
    public WebResponse batchAdd(@RequestBody List<BookPO> bookPOList){
        bookService.batchAddList(bookPOList);
        return WebResponse.success();
    }

    @ApiOperation(value = "查询")
    @GetMapping(value = "/list")
    public WebResponse list(@RequestParam(value = "labels",required = false)String labels){
       List<BookPO> list = bookService.list(labels);
       return WebResponse.success(list);
    }

    @ApiOperation(value = "更新")
    @PutMapping(value = "/update")
    public WebResponse update(@RequestBody BookPO bookPO){
        bookService.update(bookPO);
        return WebResponse.success();
    }

    @ApiOperation(value = "动态更新")
    @PutMapping(value = "/updateDynamic")
    public WebResponse updateDynamic(@RequestBody BookPO bookPO){
        bookService.updateDynamic(bookPO);
        return WebResponse.success();
    }
}
