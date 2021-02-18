package com.zjl.spring_boot_jpa.dao;

import com.zjl.spring_boot_jpa.model.BookPO;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: zhou
 * @Date: 2021-02-12 17:30
 * @Description:
 */
public interface BookDao {
    
    /** 
     * @description 批量添加书籍 
     * @author zhou       
     * @created  2021/2/17 15:33
     * @param 
     * @return int
     **/
    int addBatchBookList(List<BookPO> bookPOList);

    /**
     * @description 通过书籍名称搜索书籍
     * @author zhou
     * @created  2021/2/17 18:57
     * @param
     * @return java.util.List<com.zjl.spring_boot_jpa.model.BookPO>
     **/
    List<BookPO> getList(String name);

    /** 
     * @description 分页排序 
     * @author zhou       
     * @created  2021/2/17 19:46
     * @param
     * @param pageNum
     * @param pageSize
     * @return org.springframework.data.domain.Page<com.zjl.spring_boot_jpa.model.BookPO>
     **/
    Page<BookPO> getPageByOrder(Integer pageNum, Integer pageSize);

    /**
     * @description 动态查询
     * @author zhou
     * @created  2021/2/18 11:44
     * @param bookName 书名
     * @param maxPrice 最高价
     * @param minPrice 最低价
     * @return
     **/
    List<BookPO> queryBookList(String bookName, BigDecimal minPrice,BigDecimal maxPrice);
}
