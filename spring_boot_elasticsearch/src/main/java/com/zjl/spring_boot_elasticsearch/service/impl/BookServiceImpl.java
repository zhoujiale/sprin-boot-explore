package com.zjl.spring_boot_elasticsearch.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zjl.spring_boot_elasticsearch.model.BookPO;
import com.zjl.spring_boot_elasticsearch.repository.BookRepository;
import com.zjl.spring_boot_elasticsearch.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author zhou
 * @className BookServiceImpl
 * @descrption TODO
 * @date 2022/4/13 13:26
 */
@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RestHighLevelClient elasticsearchClient;

    @Override
    public void add(BookPO bookPO) {
        bookPO.setBookId(UUID.randomUUID().toString().replaceAll("-",""));
        bookRepository.save(bookPO);
    }

    @Override
    public List<BookPO> list(String labels) {
        List<BookPO> bookList = new ArrayList<>();
        //声明请求
        SearchRequest searchRequest = new SearchRequest("book1.1");
        //构建查询
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(StringUtils.isNotBlank(labels)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("labels", labels));
        }
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = this.getSearchResponse(searchRequest);
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            BookPO book = JSONObject.parseObject(hit.getSourceAsString(), BookPO.class);
            bookList.add(book);
        }
        return bookList;
    }

    /**
     * @description 获取查询结果
     * @author zhou
     * @create 2022/1/4 15:01
     * @param
     * @return org.elasticsearch.action.search.SearchResponse
     **/
    private SearchResponse getSearchResponse(SearchRequest searchRequest){
        SearchResponse searchResponse;
        try{
            searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);
        }catch (IOException e){
            log.error("ES服务连接异常");
            throw new RuntimeException("ES服务连接异常");
        }
        return searchResponse;
    }
}
