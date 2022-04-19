package com.zjl.spring_boot_redis.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhou
 * @className BookVO
 * @descrption 书本响应类
 * @date 2022/4/19 16:55
 */
@Data
public class BookVO implements Serializable {

    private Long bookId;

    private String bookName;
}
