package com.zjl.spring_boot_mybatis.mapper;

import static com.zjl.spring_boot_mybatis.mapper.BookPODynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.zjl.spring_boot_mybatis.model.BookPO;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
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

@Mapper
public interface BookPOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.818+08:00", comments="Source Table: tb_book")
    BasicColumn[] selectList = BasicColumn.columnList(bookId, bookName, publicationDate, price, createTime, updateTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.802+08:00", comments="Source Table: tb_book")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.802+08:00", comments="Source Table: tb_book")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.802+08:00", comments="Source Table: tb_book")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.bookId", before=false, resultType=Integer.class)
    int insert(InsertStatementProvider<BookPO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.802+08:00", comments="Source Table: tb_book")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("BookPOResult")
    Optional<BookPO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.802+08:00", comments="Source Table: tb_book")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="BookPOResult", value = {
        @Result(column="book_id", property="bookId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="book_name", property="bookName", jdbcType=JdbcType.VARCHAR),
        @Result(column="publication_date", property="publicationDate", jdbcType=JdbcType.DATE),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<BookPO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.818+08:00", comments="Source Table: tb_book")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.818+08:00", comments="Source Table: tb_book")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, bookPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.818+08:00", comments="Source Table: tb_book")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, bookPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.818+08:00", comments="Source Table: tb_book")
    default int deleteByPrimaryKey(Integer bookId_) {
        return delete(c -> 
            c.where(bookId, isEqualTo(bookId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.818+08:00", comments="Source Table: tb_book")
    default int insert(BookPO record) {
        return MyBatis3Utils.insert(this::insert, record, bookPO, c ->
            c.map(bookName).toProperty("bookName")
            .map(publicationDate).toProperty("publicationDate")
            .map(price).toProperty("price")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.818+08:00", comments="Source Table: tb_book")
    default int insertSelective(BookPO record) {
        return MyBatis3Utils.insert(this::insert, record, bookPO, c ->
            c.map(bookName).toPropertyWhenPresent("bookName", record::getBookName)
            .map(publicationDate).toPropertyWhenPresent("publicationDate", record::getPublicationDate)
            .map(price).toPropertyWhenPresent("price", record::getPrice)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.833+08:00", comments="Source Table: tb_book")
    default Optional<BookPO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, bookPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.833+08:00", comments="Source Table: tb_book")
    default List<BookPO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, bookPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.833+08:00", comments="Source Table: tb_book")
    default List<BookPO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, bookPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.833+08:00", comments="Source Table: tb_book")
    default Optional<BookPO> selectByPrimaryKey(Integer bookId_) {
        return selectOne(c ->
            c.where(bookId, isEqualTo(bookId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.833+08:00", comments="Source Table: tb_book")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, bookPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.833+08:00", comments="Source Table: tb_book")
    static UpdateDSL<UpdateModel> updateAllColumns(BookPO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(bookName).equalTo(record::getBookName)
                .set(publicationDate).equalTo(record::getPublicationDate)
                .set(price).equalTo(record::getPrice)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.833+08:00", comments="Source Table: tb_book")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(BookPO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(bookName).equalToWhenPresent(record::getBookName)
                .set(publicationDate).equalToWhenPresent(record::getPublicationDate)
                .set(price).equalToWhenPresent(record::getPrice)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.833+08:00", comments="Source Table: tb_book")
    default int updateByPrimaryKey(BookPO record) {
        return update(c ->
            c.set(bookName).equalTo(record::getBookName)
            .set(publicationDate).equalTo(record::getPublicationDate)
            .set(price).equalTo(record::getPrice)
            .set(createTime).equalTo(record::getCreateTime)
            .set(updateTime).equalTo(record::getUpdateTime)
            .where(bookId, isEqualTo(record::getBookId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.833+08:00", comments="Source Table: tb_book")
    default int updateByPrimaryKeySelective(BookPO record) {
        return update(c ->
            c.set(bookName).equalToWhenPresent(record::getBookName)
            .set(publicationDate).equalToWhenPresent(record::getPublicationDate)
            .set(price).equalToWhenPresent(record::getPrice)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .set(updateTime).equalToWhenPresent(record::getUpdateTime)
            .where(bookId, isEqualTo(record::getBookId))
        );
    }
}