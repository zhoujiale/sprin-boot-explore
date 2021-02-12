package com.zjl.spring_boot_mybatis.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OrderDetailPODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order_detail")
    public static final OrderDetailPO orderDetailPO = new OrderDetailPO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source field: tb_order_detail.order_detail_id")
    public static final SqlColumn<Integer> orderDetailId = orderDetailPO.orderDetailId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source field: tb_order_detail.order_id")
    public static final SqlColumn<Integer> orderId = orderDetailPO.orderId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source field: tb_order_detail.book_id")
    public static final SqlColumn<Integer> bookId = orderDetailPO.bookId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source field: tb_order_detail.book_count")
    public static final SqlColumn<Integer> bookCount = orderDetailPO.bookCount;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source field: tb_order_detail.unit_price")
    public static final SqlColumn<BigDecimal> unitPrice = orderDetailPO.unitPrice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source field: tb_order_detail.create_time")
    public static final SqlColumn<LocalDateTime> createTime = orderDetailPO.createTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source field: tb_order_detail.update_time")
    public static final SqlColumn<LocalDateTime> updateTime = orderDetailPO.updateTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order_detail")
    public static final class OrderDetailPO extends SqlTable {
        public final SqlColumn<Integer> orderDetailId = column("order_detail_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> orderId = column("order_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> bookId = column("book_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> bookCount = column("book_count", JDBCType.INTEGER);

        public final SqlColumn<BigDecimal> unitPrice = column("unit_price", JDBCType.DECIMAL);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public OrderDetailPO() {
            super("tb_order_detail");
        }
    }
}