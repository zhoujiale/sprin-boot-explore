package com.github.zhoujiale.spring.boot.redis.controller;
import com.github.zhoujiale.commons.util.response.WebResponse;
import com.github.zhoujiale.spring.boot.redis.domain.BookVO;
import com.github.zhoujiale.spring.boot.redis.entity.BookPO;
import com.github.zhoujiale.spring.boot.redis.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhou
 * @className BookController
 * @descrption 书籍controller
 * @date 2022/4/19 14:45
 */
@Api(value = "书籍模块",tags = {"书籍模块"})
@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "添加书本")
    @PostMapping(value = "/add")
    public WebResponse add(@RequestBody BookPO bookPO){
        bookService.add(bookPO);
        return WebResponse.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping(value = "/update/{id}")
    public WebResponse update(@PathVariable(value = "id")Long id,
                              @RequestBody BookPO bookPO){
        bookService.update(id,bookPO);
        return WebResponse.success();
    }

    @ApiOperation(value = "获取书本")
    @GetMapping(value = "/one/{id}")
    public WebResponse get(@PathVariable(value = "id")Long id){
        BookVO bookVO = bookService.get(id);
        return WebResponse.success(bookVO);
    }

    @ApiOperation(value = "删除书本")
    @DeleteMapping(value = "/delete/{id}")
    public WebResponse delete(@PathVariable(value = "id")Long id){
        bookService.delete(id);
        return WebResponse.success();
    }
}
