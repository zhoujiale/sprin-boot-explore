package com.zjl.spring_boot_redis.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author zhou
 * @version 1.0
 * @className BookPO
 * @description
 * @date 2021/04/20 20:59
 **/
@Data
@Table(name = "tb_book")
@Entity
@org.hibernate.annotations.Table(appliesTo = "tb_book",comment = "书籍表")
@EntityListeners(AuditingEntityListener.class)
public class BookPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id",nullable = false,columnDefinition = "bigint(20) comment 'id'")
    private Long bookId;
    @Column(name = "book_name",nullable = false,columnDefinition = "varchar(45) comment '书名'")
    private String bookName;
    @Column(name = "create_date",nullable = false,columnDefinition = "datetime default current_timestamp")
    private LocalDateTime createDate;
    @Column(name = "update_date",nullable = false,columnDefinition = "datetime default current_timestamp on update current_timestamp")
    private LocalDateTime updateDate;


}
