package com.zjl.spring_boot_elasticsearch.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author zhou
 * @version 1.0
 * @className BookPO
 * @description 书籍
 * @date 2022/01/04 11:08
 **/
@Data
@Document(indexName = "book",createIndex = false)
public class BookPO {

    @Id
    @Field(type = FieldType.Keyword,name = "book_id")
    private String bookId;

    @Field(type = FieldType.Text,name = "book_name")
    private String bookName;


}
