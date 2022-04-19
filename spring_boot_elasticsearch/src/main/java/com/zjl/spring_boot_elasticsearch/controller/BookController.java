package com.zjl.spring_boot_elasticsearch.controller;

import com.zjl.commons.util.response.WebResponse;
import com.zjl.spring_boot_elasticsearch.model.BookPO;
import com.zjl.spring_boot_elasticsearch.service.BookService;
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

    @ApiOperation(value = "/查询")
    @GetMapping(value = "/list")
    public WebResponse list(@RequestParam(value = "labels",required = false)String labels){
       List<BookPO> list = bookService.list(labels);
       return WebResponse.success(list);
    }
}
