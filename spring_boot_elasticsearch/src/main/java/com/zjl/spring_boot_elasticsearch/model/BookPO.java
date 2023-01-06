package com.zjl.spring_boot_elasticsearch.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

/**
 * @author zhou
 * @version 1.0
 * @className BookPO
 * @description 书籍
 * @date 2022/01/04 11:08
 **/
@Data
@Document(indexName = "my_book",createIndex = false)
public class BookPO {

    @Id
    @Field(type = FieldType.Keyword,name = "book_id")
    private String bookId;

    @Field(type = FieldType.Text,name = "book_name")
    private String bookName;

    @Field(type = FieldType.Text,name = "labels")
    private String labels;

    @Field(type = FieldType.Double,name = "price")
    private BigDecimal price;
}
