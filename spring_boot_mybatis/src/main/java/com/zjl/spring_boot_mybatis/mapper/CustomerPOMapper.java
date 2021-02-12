package com.zjl.spring_boot_mybatis.mapper;

import static com.zjl.spring_boot_mybatis.mapper.CustomerPODynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.zjl.spring_boot_mybatis.model.CustomerPO;
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
public interface CustomerPOMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    BasicColumn[] selectList = BasicColumn.columnList(customerId, customerName, createTime, updateTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.customerId", before=false, resultType=Integer.class)
    int insert(InsertStatementProvider<CustomerPO> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CustomerPOResult")
    Optional<CustomerPO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CustomerPOResult", value = {
        @Result(column="customer_id", property="customerId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="customer_name", property="customerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<CustomerPO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, customerPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, customerPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    default int deleteByPrimaryKey(Integer customerId_) {
        return delete(c -> 
            c.where(customerId, isEqualTo(customerId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    default int insert(CustomerPO record) {
        return MyBatis3Utils.insert(this::insert, record, customerPO, c ->
            c.map(customerName).toProperty("customerName")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    default int insertSelective(CustomerPO record) {
        return MyBatis3Utils.insert(this::insert, record, customerPO, c ->
            c.map(customerName).toPropertyWhenPresent("customerName", record::getCustomerName)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    default Optional<CustomerPO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, customerPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    default List<CustomerPO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, customerPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    default List<CustomerPO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, customerPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    default Optional<CustomerPO> selectByPrimaryKey(Integer customerId_) {
        return selectOne(c ->
            c.where(customerId, isEqualTo(customerId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, customerPO, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    static UpdateDSL<UpdateModel> updateAllColumns(CustomerPO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(customerName).equalTo(record::getCustomerName)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(CustomerPO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(customerName).equalToWhenPresent(record::getCustomerName)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    default int updateByPrimaryKey(CustomerPO record) {
        return update(c ->
            c.set(customerName).equalTo(record::getCustomerName)
            .set(createTime).equalTo(record::getCreateTime)
            .set(updateTime).equalTo(record::getUpdateTime)
            .where(customerId, isEqualTo(record::getCustomerId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-02-12T11:49:30.849+08:00", comments="Source Table: tb_customer")
    default int updateByPrimaryKeySelective(CustomerPO record) {
        return update(c ->
            c.set(customerName).equalToWhenPresent(record::getCustomerName)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .set(updateTime).equalToWhenPresent(record::getUpdateTime)
            .where(customerId, isEqualTo(record::getCustomerId))
        );
    }
}