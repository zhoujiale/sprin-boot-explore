package com.github.zhoujiale.spring.boot.mongodb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zhou
 * @version 1.0
 * @className ProductPO
 * @description
 * @date 2021/12/21 11:38
 **/
@Data
@Document(collection = "product")
public class ProductPO implements Serializable {

    @Id
    private String id;

    @Field(targetType = FieldType.STRING,name = "product_name")
    private String productName;

    @Field(targetType = FieldType.DECIMAL128,name = "price")
    private BigDecimal price;

    @Field(targetType = FieldType.DATE_TIME,name = "create_date")
    private LocalDateTime createDate;

    @Field(targetType = FieldType.DATE_TIME,name = "update_date")
    private LocalDateTime updateDate;
}
