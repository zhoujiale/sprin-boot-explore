package com.github.zhoujiale.spring.boot.mybatis.model;

import javax.annotation.Generated;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderPO {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source field: tb_order.order_id")
    private Integer orderId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source field: tb_order.customer_id")
    private Integer customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source field: tb_order.price")
    private BigDecimal price;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order.create_time")
    private LocalDateTime createTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order.update_time")
    private LocalDateTime updateTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source field: tb_order.order_id")
    public Integer getOrderId() {
        return orderId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source field: tb_order.order_id")
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source field: tb_order.customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source field: tb_order.customer_id")
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source field: tb_order.price")
    public BigDecimal getPrice() {
        return price;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source field: tb_order.price")
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order.create_time")
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order.create_time")
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order.update_time")
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order.update_time")
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}