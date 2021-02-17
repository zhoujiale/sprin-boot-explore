package com.zjl.commons.util.page;

import lombok.Data;

import java.util.List;

/**
 * @name: PageVO
 * @description:
 * @author: zhou
 * @create: 2021-02-17 11:25
 */
@Data
public class PageVO<T> {

    /**页码**/
    private Integer pageNum;
    /**每页大小**/
    private Integer pageSize;
    /**总页数**/
    private Integer totalPage;
    /**总记录数**/
    private Long totalCount;
    /**记录**/
    private List<T> list;

    public static <T> PageVO pageOf(List<T> list,Integer pageNum,Integer pageSize,long totalCount){
        PageVO pageVO = new PageVO();
        pageVO.setList(list);
        pageVO.setPageNum(pageNum);
        pageVO.setPageSize(pageSize);
        pageVO.setTotalCount(totalCount);
        long i = totalCount % pageSize;
        if (i == 0){
            pageVO.setTotalPage(Math.toIntExact(i));
        }else {
            pageVO.setTotalPage(Math.toIntExact(totalCount / pageSize + 1));
        }
        return pageVO;
    }


}
