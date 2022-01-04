package com.zjl.spring_boot_elasticsearch.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @name: ProductPO
 * @description: 商品类
 * @author: zhou
 * @create: 2021-12-11 14:38
 */
@Data
@Document(indexName = "product", createIndex = true)
public class ProductPO {

    /**
     * id
     **/
    @Id
    @Field(type = FieldType.Keyword, name = "product_id")
    private String productId;

    /**
     * 商品名称
     */
    @Field(type = FieldType.Text, name = "product_name", searchAnalyzer = "ik_max_word")
    private String productName;

    @Field(type = FieldType.Keyword,name = "category")
    private String category;

    /**
     * 描述
     **/
    @Field(type = FieldType.Keyword, name = "description")
    private String description;

    /**
     * 价格
     */
    @Field(type = FieldType.Double, name = "price")
    private BigDecimal price;

    /**
     * 价格范围
     */
    @Field(type = FieldType.Double_Range)
    private ValidPrice validPrice;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Date, name = "create_date", format = DateFormat.date_time_no_millis)
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    @Field(type = FieldType.Date, name = "update_date", format = DateFormat.date_time_no_millis)
    private LocalDateTime updateDate;

    class ValidPrice {

        @Field(type = FieldType.Double, name = "min_price")
        private BigDecimal minPrice;

        @Field(type = FieldType.Double, name = "max_price")
        private BigDecimal maxPrice;
    }
}
