package com.zjl.spring_boot_mybatis.mapper;

import static com.zjl.spring_boot_mybatis.mapper.OrderDetailPODynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.zjl.spring_boot_mybatis.model.OrderDetailPO;
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
public interface OrderDetailPOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source Table: tb_order_detail")
    BasicColumn[] selectList = BasicColumn.columnList(orderDetailId, orderId, bookId, bookCount, unitPrice, createTime, updateTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source Table: tb_order_detail")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source Table: tb_order_detail")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source Table: tb_order_detail")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.orderDetailId", before=false, resultType=Integer.class)
    int insert(InsertStatementProvider<OrderDetailPO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source Table: tb_order_detail")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OrderDetailPOResult")
    Optional<OrderDetailPO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source Table: tb_order_detail")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OrderDetailPOResult", value = {
        @Result(column="order_detail_id", property="orderDetailId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.INTEGER),
        @Result(column="book_id", property="bookId", jdbcType=JdbcType.INTEGER),
        @Result(column="book_count", property="bookCount", jdbcType=JdbcType.INTEGER),
        @Result(column="unit_price", property="unitPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<OrderDetailPO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source Table: tb_order_detail")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source Table: tb_order_detail")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, orderDetailPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source Table: tb_order_detail")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, orderDetailPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source Table: tb_order_detail")
    default int deleteByPrimaryKey(Integer orderDetailId_) {
        return delete(c -> 
            c.where(orderDetailId, isEqualTo(orderDetailId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source Table: tb_order_detail")
    default int insert(OrderDetailPO record) {
        return MyBatis3Utils.insert(this::insert, record, orderDetailPO, c ->
            c.map(orderId).toProperty("orderId")
            .map(bookId).toProperty("bookId")
            .map(bookCount).toProperty("bookCount")
            .map(unitPrice).toProperty("unitPrice")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source Table: tb_order_detail")
    default int insertSelective(OrderDetailPO record) {
        return MyBatis3Utils.insert(this::insert, record, orderDetailPO, c ->
            c.map(orderId).toPropertyWhenPresent("orderId", record::getOrderId)
            .map(bookId).toPropertyWhenPresent("bookId", record::getBookId)
            .map(bookCount).toPropertyWhenPresent("bookCount", record::getBookCount)
            .map(unitPrice).toPropertyWhenPresent("unitPrice", record::getUnitPrice)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source Table: tb_order_detail")
    default Optional<OrderDetailPO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, orderDetailPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source Table: tb_order_detail")
    default List<OrderDetailPO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, orderDetailPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source Table: tb_order_detail")
    default List<OrderDetailPO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, orderDetailPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source Table: tb_order_detail")
    default Optional<OrderDetailPO> selectByPrimaryKey(Integer orderDetailId_) {
        return selectOne(c ->
            c.where(orderDetailId, isEqualTo(orderDetailId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source Table: tb_order_detail")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, orderDetailPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source Table: tb_order_detail")
    static UpdateDSL<UpdateModel> updateAllColumns(OrderDetailPO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(orderId).equalTo(record::getOrderId)
                .set(bookId).equalTo(record::getBookId)
                .set(bookCount).equalTo(record::getBookCount)
                .set(unitPrice).equalTo(record::getUnitPrice)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source Table: tb_order_detail")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(OrderDetailPO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(orderId).equalToWhenPresent(record::getOrderId)
                .set(bookId).equalToWhenPresent(record::getBookId)
                .set(bookCount).equalToWhenPresent(record::getBookCount)
                .set(unitPrice).equalToWhenPresent(record::getUnitPrice)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source Table: tb_order_detail")
    default int updateByPrimaryKey(OrderDetailPO record) {
        return update(c ->
            c.set(orderId).equalTo(record::getOrderId)
            .set(bookId).equalTo(record::getBookId)
            .set(bookCount).equalTo(record::getBookCount)
            .set(unitPrice).equalTo(record::getUnitPrice)
            .set(createTime).equalTo(record::getCreateTime)
            .set(updateTime).equalTo(record::getUpdateTime)
            .where(orderDetailId, isEqualTo(record::getOrderDetailId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.88+08:00", comments="Source Table: tb_order_detail")
    default int updateByPrimaryKeySelective(OrderDetailPO record) {
        return update(c ->
            c.set(orderId).equalToWhenPresent(record::getOrderId)
            .set(bookId).equalToWhenPresent(record::getBookId)
            .set(bookCount).equalToWhenPresent(record::getBookCount)
            .set(unitPrice).equalToWhenPresent(record::getUnitPrice)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .set(updateTime).equalToWhenPresent(record::getUpdateTime)
            .where(orderDetailId, isEqualTo(record::getOrderDetailId))
        );
    }
}