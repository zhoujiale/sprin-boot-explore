package com.zjl.spring_boot_mybatis.model;

import lombok.ToString;

import javax.annotation.Generated;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@ToString
public class BookPO {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.787+08:00", comments="Source field: tb_book.book_id")
    private Integer bookId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.787+08:00", comments="Source field: tb_book.book_name")
    private String bookName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.787+08:00", comments="Source field: tb_book.publication_date")
    private LocalDate publicationDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.787+08:00", comments="Source field: tb_book.price")
    private BigDecimal price;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.787+08:00", comments="Source field: tb_book.create_time")
    private LocalDateTime createTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.787+08:00", comments="Source field: tb_book.update_time")
    private LocalDateTime updateTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.787+08:00", comments="Source field: tb_book.book_id")
    public Integer getBookId() {
        return bookId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.787+08:00", comments="Source field: tb_book.book_id")
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.787+08:00", comments="Source field: tb_book.book_name")
    public String getBookName() {
        return bookName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.787+08:00", comments="Source field: tb_book.book_name")
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.787+08:00", comments="Source field: tb_book.publication_date")
    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.787+08:00", comments="Source field: tb_book.publication_date")
    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.787+08:00", comments="Source field: tb_book.price")
    public BigDecimal getPrice() {
        return price;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.787+08:00", comments="Source field: tb_book.price")
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.787+08:00", comments="Source field: tb_book.create_time")
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.787+08:00", comments="Source field: tb_book.create_time")
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.787+08:00", comments="Source field: tb_book.update_time")
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.787+08:00", comments="Source field: tb_book.update_time")
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}