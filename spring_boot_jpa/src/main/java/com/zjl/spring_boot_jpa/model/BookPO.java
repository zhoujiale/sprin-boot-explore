package com.zjl.spring_boot_jpa.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @name: BookPO
 * @description:
 * @author: zhou
 * @create: 2021-02-12 15:23
 */
@Data
@Entity
@Table(name = "tb_book")
@org.hibernate.annotations.Table(appliesTo = "tb_book",comment = "电子书")
@EntityListeners(AuditingEntityListener.class)
public class BookPO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id",nullable = false,columnDefinition = "int(11) comment '书籍id'")
    private Integer bookId;
    @Column(name = "book_name",nullable = false,columnDefinition = "varchar(45) comment '书籍名称'")
    private String bookName;
    @Column(name = "publication_date",nullable = false,columnDefinition = "date comment '出版时间'")
    private LocalDate publicationDate;
    @Column(name = "price",nullable = false,columnDefinition = "decimal(6,2) comment '价格'")
    private BigDecimal price;
    @CreatedDate
    @Column(name = "create_time",nullable = false,columnDefinition = "datetime default current_timestamp")
    private LocalDateTime createTime;
    @LastModifiedDate
    @Column(name = "update_time",nullable = false,columnDefinition = "datetime default current_timestamp on update current_timestamp")
    private LocalDateTime updateTime;
}
