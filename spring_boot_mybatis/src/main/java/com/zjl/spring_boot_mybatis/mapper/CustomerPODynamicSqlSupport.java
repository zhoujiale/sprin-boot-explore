package com.zjl.spring_boot_mybatis.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CustomerPODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    public static final CustomerPO customerPO = new CustomerPO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source field: tb_customer.customer_id")
    public static final SqlColumn<Integer> customerId = customerPO.customerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source field: tb_customer.customer_name")
    public static final SqlColumn<String> customerName = customerPO.customerName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source field: tb_customer.create_time")
    public static final SqlColumn<LocalDateTime> createTime = customerPO.createTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source field: tb_customer.update_time")
    public static final SqlColumn<LocalDateTime> updateTime = customerPO.updateTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    public static final class CustomerPO extends SqlTable {
        public final SqlColumn<Integer> customerId = column("customer_id", JDBCType.INTEGER);

        public final SqlColumn<String> customerName = column("customer_name", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public CustomerPO() {
            super("tb_customer");
        }
    }
}