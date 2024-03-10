package com.github.zhoujiale.spring.boot.mybatis.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @name: BookVO
 * @description:
 * @author: zhou
 * @create: 2021-02-12 13:30
 */
@Data
public class BookVO {

    private Integer bookId;

    private String bookName;

    private LocalDate publicationDate;

    private BigDecimal price;
}
