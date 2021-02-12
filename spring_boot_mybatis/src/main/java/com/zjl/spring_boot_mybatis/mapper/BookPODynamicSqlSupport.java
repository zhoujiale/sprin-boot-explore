package com.zjl.spring_boot_mybatis.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class BookPODynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.802+08:00", comments="Source Table: tb_book")
    public static final BookPO bookPO = new BookPO();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.802+08:00", comments="Source field: tb_book.book_id")
    public static final SqlColumn<Integer> bookId = bookPO.bookId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.802+08:00", comments="Source field: tb_book.book_name")
    public static final SqlColumn<String> bookName = bookPO.bookName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.802+08:00", comments="Source field: tb_book.publication_date")
    public static final SqlColumn<LocalDate> publicationDate = bookPO.publicationDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.802+08:00", comments="Source field: tb_book.price")
    public static final SqlColumn<BigDecimal> price = bookPO.price;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.802+08:00", comments="Source field: tb_book.create_time")
    public static final SqlColumn<LocalDateTime> createTime = bookPO.createTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.802+08:00", comments="Source field: tb_book.update_time")
    public static final SqlColumn<LocalDateTime> updateTime = bookPO.updateTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.802+08:00", comments="Source Table: tb_book")
    public static final class BookPO extends SqlTable {
        public final SqlColumn<Integer> bookId = column("book_id", JDBCType.INTEGER);

        public final SqlColumn<String> bookName = column("book_name", JDBCType.VARCHAR);

        public final SqlColumn<LocalDate> publicationDate = column("publication_date", JDBCType.DATE);

        public final SqlColumn<BigDecimal> price = column("price", JDBCType.DECIMAL);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public BookPO() {
            super("tb_book");
        }
    }
}