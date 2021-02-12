package com.zjl.spring_boot_mybatis.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OrderPODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    public static final OrderPO orderPO = new OrderPO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order.order_id")
    public static final SqlColumn<Integer> orderId = orderPO.orderId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order.customer_id")
    public static final SqlColumn<Integer> customerId = orderPO.customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order.price")
    public static final SqlColumn<BigDecimal> price = orderPO.price;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order.create_time")
    public static final SqlColumn<LocalDateTime> createTime = orderPO.createTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source field: tb_order.update_time")
    public static final SqlColumn<LocalDateTime> updateTime = orderPO.updateTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    public static final class OrderPO extends SqlTable {
        public final SqlColumn<Integer> orderId = column("order_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> customerId = column("customer_id", JDBCType.INTEGER);

        public final SqlColumn<BigDecimal> price = column("price", JDBCType.DECIMAL);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public OrderPO() {
            super("tb_order");
        }
    }
}