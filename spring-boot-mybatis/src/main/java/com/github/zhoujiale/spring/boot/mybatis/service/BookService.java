package com.github.zhoujiale.spring.boot.mybatis.service;

import com.github.zhoujiale.commons.util.page.PageVO;
import com.github.zhoujiale.spring.boot.mybatis.model.BookPO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: zhou
 * @Date: 2021-02-12 11:50
 * @Description:
 */
public interface BookService {
    
    /** 
     * @description 添加书籍 
     * @author zhou       
     * @created  2021/2/14 20:23
     * @param bookPO
     * @return int
     **/
    int addBook(BookPO bookPO);
    
    /** 
     * @description 修改书籍 
     * @author zhou       
     * @created  2021/2/14 21:19
     * @param 
     * @return int
     **/
    int update(BookPO bookPO);

    /** 
     * @description 批量添加书籍 
     * @author zhou       
     * @created  2021/2/14 20:07
     * @param bookPOList 书籍
     * @return void
     **/
    void addBooks(List<BookPO> bookPOList);

    /** 
     * @description 查询所有的书籍 
     * @author zhou       
     * @created  2021/2/14 20:07
     * @param 
     * @return java.util.List<com.github.zhoujiale.spring_boot_mybatis.model.BookPO>
     **/
    List<BookPO> queryList(BookPO bookPO);

    /** 
     * @description 通过名称查询姓名 
     * @author zhou       
     * @created  2021/2/14 20:08
     * @param 
     * @return com.github.zhoujiale.spring_boot_mybatis.model.BookPO
     **/
    BookPO queryByName(String name);

    /**
     * @description 通过书名修改价格
     * @author zhou
     * @created  2021/2/14 21:39
     * @param
     * @return int
     **/
    int updatePrice(BigDecimal price, String bookName);

    /**
     * @description 按发布时间倒序
     * @author zhou
     * @created  2021/2/16 21:25
     * @param
     * @return java.util.List<com.github.zhoujiale.spring_boot_mybatis.model.BookPO>
     **/
    List<BookPO> orderByTime();

    /**
     * @description 分页获取
     * @author zhou
     * @created  2021/2/17 11:37
     * @param pageNum
     * @param pageSize
     * @return com.github.zhoujiale.commons.util.page.PageVO<com.github.zhoujiale.spring_boot_mybatis.model.BookPO>
     **/
    PageVO<BookPO> getPage(Integer pageNum,Integer pageSize);
    
    /** 
     * @description 书籍按名称分组 
     * @author zhou       
     * @created  2021/2/17 11:57
     * @param name
     * @return java.util.List<com.github.zhoujiale.spring_boot_mybatis.model.BookPO>
     **/
    List<BookPO> groupByPublication(String name);
}
