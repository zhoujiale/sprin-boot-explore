package com.github.zhoujiale.spring.boot.elasticsearch;

import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author zhou
 * @version 1.0
 * @className ElasticsearchRestClientTest
 * @description
 * @date 2021/12/29 21:50
 **/
@SpringBootTest
public class ElasticsearchRestClientTest {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    /**
     * 创建索引
     **/
    @Test
    public void createIndex() throws IOException {
        CreateIndexResponse createIndexResponse = restHighLevelClient
                .indices()
                //索引名称
                .create(new CreateIndexRequest("book")
                                //索引别名
                                .alias(new Alias("my_book").writeIndex(true))
                        , RequestOptions.DEFAULT);
        System.out.println(createIndexResponse.index());
        System.out.println(createIndexResponse.isAcknowledged());
    }
}
