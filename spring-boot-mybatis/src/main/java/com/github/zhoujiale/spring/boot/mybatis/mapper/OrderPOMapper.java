package com.github.zhoujiale.spring.boot.mybatis.mapper;

import com.github.zhoujiale.spring.boot.mybatis.model.OrderPO;
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

import static com.github.zhoujiale.spring.boot.mybatis.mapper.OrderPODynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface OrderPOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    BasicColumn[] selectList = BasicColumn.columnList(orderId, customerId, price, createTime, updateTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.orderId", before=false, resultType=Integer.class)
    int insert(InsertStatementProvider<OrderPO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OrderPOResult")
    Optional<OrderPO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OrderPOResult", value = {
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="customer_id", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<OrderPO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, orderPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, orderPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    default int deleteByPrimaryKey(Integer orderId_) {
        return delete(c -> 
            c.where(orderId, isEqualTo(orderId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    default int insert(OrderPO record) {
        return MyBatis3Utils.insert(this::insert, record, orderPO, c ->
            c.map(customerId).toProperty("customerId")
            .map(price).toProperty("price")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    default int insertSelective(OrderPO record) {
        return MyBatis3Utils.insert(this::insert, record, orderPO, c ->
            c.map(customerId).toPropertyWhenPresent("customerId", record::getCustomerId)
            .map(price).toPropertyWhenPresent("price", record::getPrice)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    default Optional<OrderPO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, orderPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    default List<OrderPO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, orderPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    default List<OrderPO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, orderPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    default Optional<OrderPO> selectByPrimaryKey(Integer orderId_) {
        return selectOne(c ->
            c.where(orderId, isEqualTo(orderId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, orderPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    static UpdateDSL<UpdateModel> updateAllColumns(OrderPO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(customerId).equalTo(record::getCustomerId)
                .set(price).equalTo(record::getPrice)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(OrderPO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(customerId).equalToWhenPresent(record::getCustomerId)
                .set(price).equalToWhenPresent(record::getPrice)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    default int updateByPrimaryKey(OrderPO record) {
        return update(c ->
            c.set(customerId).equalTo(record::getCustomerId)
            .set(price).equalTo(record::getPrice)
            .set(createTime).equalTo(record::getCreateTime)
            .set(updateTime).equalTo(record::getUpdateTime)
            .where(orderId, isEqualTo(record::getOrderId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.865+08:00", comments="Source Table: tb_order")
    default int updateByPrimaryKeySelective(OrderPO record) {
        return update(c ->
            c.set(customerId).equalToWhenPresent(record::getCustomerId)
            .set(price).equalToWhenPresent(record::getPrice)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .set(updateTime).equalToWhenPresent(record::getUpdateTime)
            .where(orderId, isEqualTo(record::getOrderId))
        );
    }
}