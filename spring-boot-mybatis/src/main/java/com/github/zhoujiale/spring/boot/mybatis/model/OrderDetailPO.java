package com.github.zhoujiale.spring.boot.mybatis.model;

import javax.annotation.Generated;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDetailPO {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order_detail.order_detail_id")
    private Integer orderDetailId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order_detail.order_id")
    private Integer orderId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order_detail.book_id")
    private Integer bookId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order_detail.book_count")
    private Integer bookCount;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order_detail.unit_price")
    private BigDecimal unitPrice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order_detail.create_time")
    private LocalDateTime createTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order_detail.update_time")
    private LocalDateTime updateTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order_detail.order_detail_id")
    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order_detail.order_detail_id")
    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order_detail.order_id")
    public Integer getOrderId() {
        return orderId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order_detail.order_id")
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order_detail.book_id")
    public Integer getBookId() {
        return bookId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order_detail.book_id")
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order_detail.book_count")
    public Integer getBookCount() {
        return bookCount;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order_detail.book_count")
    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order_detail.unit_price")
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order_detail.unit_price")
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order_detail.create_time")
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order_detail.create_time")
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order_detail.update_time")
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order_detail.update_time")
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}