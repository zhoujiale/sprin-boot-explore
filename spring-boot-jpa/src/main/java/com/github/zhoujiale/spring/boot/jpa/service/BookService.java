package com.github.zhoujiale.spring.boot.jpa.service;

import com.github.zhoujiale.commons.util.page.PageVO;
import com.github.zhoujiale.spring.boot.jpa.model.BookPO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: zhou
 * @Date: 2021-02-17 15:23
 * @Description:
 */
public interface BookService {

    /**
     * @description 批量添加书籍
     * @author zhou
     * @created  2021/2/17 15:27
     * @param
     * @return int
     **/
    int addBookList(List<BookPO> bookPOList);

    /** 
     * @description 模糊查询 
     * @author zhou       
     * @created  2021/2/17 18:45
     * @param 
     * @return java.util.List<com.github.zhoujiale.spring_boot_jpa.model.BookPO>
     **/
    List<BookPO> getList(String name);
    
    /** 
     * @description 分页排序 
     * @author zhou       
     * @created  2021/2/17 19:40
     * @param 
     * @return com.github.zhoujiale.commons.util.page.PageVO<com.github.zhoujiale.spring_boot_jpa.model.BookPO>
     **/
    PageVO<BookPO> getPageByOrder(Integer pageNum,Integer pageSize);

    /**
     * @description 动态查询
     * @author zhou
     * @created  2021/2/18 11:57
     * @param bookName 书名
     * @param maxPrice 最高价
     * @param minPrice 最低价
     * @return java.util.List<com.github.zhoujiale.spring_boot_jpa.model.BookPO>
     **/
    List<BookPO> queryDynamicBook(String bookName, BigDecimal minPrice, BigDecimal maxPrice);
}
