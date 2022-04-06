package com.zjl.spring_boot_mybatis.mapper;

import com.zjl.spring_boot_mybatis.model.BookPO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;

import static com.zjl.spring_boot_mybatis.mapper.BookPODynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface BookPOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T12:10:56.602+08:00", comments="Source Table: tb_book")
    BasicColumn[] selectList = BasicColumn.columnList(id, bookName, bookNumber, price, bookId, createTime, publicationDate, updateTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T12:10:56.586+08:00", comments="Source Table: tb_book")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T12:10:56.588+08:00", comments="Source Table: tb_book")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T12:10:56.589+08:00", comments="Source Table: tb_book")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.bookId", before=false, resultType=Integer.class)
    int insert(InsertStatementProvider<BookPO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T12:10:56.592+08:00", comments="Source Table: tb_book")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("BookPOResult")
    Optional<BookPO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T12:10:56.593+08:00", comments="Source Table: tb_book")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="BookPOResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="book_name", property="bookName", jdbcType=JdbcType.VARCHAR),
        @Result(column="book_number", property="bookNumber", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="book_id", property="bookId", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="publication_date", property="publicationDate", jdbcType=JdbcType.DATE),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<BookPO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T12:10:56.594+08:00", comments="Source Table: tb_book")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T12:10:56.595+08:00", comments="Source Table: tb_book")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, bookPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T12:10:56.595+08:00", comments="Source Table: tb_book")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, bookPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T12:10:56.596+08:00", comments="Source Table: tb_book")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T12:10:56.598+08:00", comments="Source Table: tb_book")
    default int insert(BookPO record) {
        return MyBatis3Utils.insert(this::insert, record, bookPO, c ->
            c.map(id).toProperty("id")
            .map(bookName).toProperty("bookName")
            .map(bookNumber).toProperty("bookNumber")
            .map(price).toProperty("price")
            .map(createTime).toProperty("createTime")
            .map(publicationDate).toProperty("publicationDate")
            .map(updateTime).toProperty("updateTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T12:10:56.6+08:00", comments="Source Table: tb_book")
    default int insertSelective(BookPO record) {
        return MyBatis3Utils.insert(this::insert, record, bookPO, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(bookName).toPropertyWhenPresent("bookName", record::getBookName)
            .map(bookNumber).toPropertyWhenPresent("bookNumber", record::getBookNumber)
            .map(price).toPropertyWhenPresent("price", record::getPrice)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(publicationDate).toPropertyWhenPresent("publicationDate", record::getPublicationDate)
            .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T12:10:56.604+08:00", comments="Source Table: tb_book")
    default Optional<BookPO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, bookPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T12:10:56.605+08:00", comments="Source Table: tb_book")
    default List<BookPO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, bookPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T12:10:56.606+08:00", comments="Source Table: tb_book")
    default List<BookPO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, bookPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T12:10:56.606+08:00", comments="Source Table: tb_book")
    default Optional<BookPO> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T12:10:56.607+08:00", comments="Source Table: tb_book")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, bookPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T12:10:56.607+08:00", comments="Source Table: tb_book")
    static UpdateDSL<UpdateModel> updateAllColumns(BookPO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(bookName).equalTo(record::getBookName)
                .set(bookNumber).equalTo(record::getBookNumber)
                .set(price).equalTo(record::getPrice)
                .set(createTime).equalTo(record::getCreateTime)
                .set(publicationDate).equalTo(record::getPublicationDate)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T12:10:56.608+08:00", comments="Source Table: tb_book")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(BookPO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(bookName).equalToWhenPresent(record::getBookName)
                .set(bookNumber).equalToWhenPresent(record::getBookNumber)
                .set(price).equalToWhenPresent(record::getPrice)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(publicationDate).equalToWhenPresent(record::getPublicationDate)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T12:10:56.609+08:00", comments="Source Table: tb_book")
    default int updateByPrimaryKey(BookPO record) {
        return update(c ->
            c.set(bookName).equalTo(record::getBookName)
            .set(bookNumber).equalTo(record::getBookNumber)
            .set(price).equalTo(record::getPrice)
            .set(createTime).equalTo(record::getCreateTime)
            .set(publicationDate).equalTo(record::getPublicationDate)
            .set(updateTime).equalTo(record::getUpdateTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T12:10:56.61+08:00", comments="Source Table: tb_book")
    default int updateByPrimaryKeySelective(BookPO record) {
        return update(c ->
            c.set(bookName).equalToWhenPresent(record::getBookName)
            .set(bookNumber).equalToWhenPresent(record::getBookNumber)
            .set(price).equalToWhenPresent(record::getPrice)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .set(publicationDate).equalToWhenPresent(record::getPublicationDate)
            .set(updateTime).equalToWhenPresent(record::getUpdateTime)
            .where(id, isEqualTo(record::getId))
        );
    }
}