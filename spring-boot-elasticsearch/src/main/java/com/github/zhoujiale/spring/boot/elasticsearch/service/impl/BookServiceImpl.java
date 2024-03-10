package com.github.zhoujiale.spring.boot.elasticsearch.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.github.zhoujiale.spring.boot.elasticsearch.model.BookPO;
import com.github.zhoujiale.spring.boot.elasticsearch.repository.BookRepository;
import com.github.zhoujiale.spring.boot.elasticsearch.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

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
        bookPO.setBookId(UUID.randomUUID().toString().replaceAll("-", ""));
        bookRepository.save(bookPO);
    }

    @Override
    public void batchAddList(List<BookPO> bookPOList) {
        //批量操作 bulk api
        BulkRequest bulkRequest = new BulkRequest();
        Map<String, Object> map;
        for (BookPO bookPO : bookPOList) {
            map = new HashMap<>(6);
            map.put("book_name", bookPO.getBookName());
            map.put("labels", bookPO.getLabels());
            map.put("price", bookPO.getPrice());
            IndexRequest indexRequest = new IndexRequest()
                    .index("my_book")
                    .source(map, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        BulkResponse bulkResponse;
        try {
            bulkResponse = elasticsearchClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public List<BookPO> list(String labels) {
        List<BookPO> bookList = new ArrayList<>();
        //声明请求
        SearchRequest searchRequest = new SearchRequest("book1.1");
        //构建查询
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (StringUtils.isNotBlank(labels)) {
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

    @Override
    public void update(BookPO bookPO) {
        bookRepository.save(bookPO);
    }

    @Override
    public void updateDynamic(BookPO bookPO) {
        //通过id更新
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("my_book").id(bookPO.getBookId());
        if (StringUtils.isNotBlank(bookPO.getBookName())) {
            updateRequest.doc(XContentType.JSON, "book_name", bookPO.getBookName());
        }
        if (StringUtils.isNotBlank(bookPO.getLabels())) {
            updateRequest.doc(XContentType.JSON, "labels", bookPO.getLabels());
        }
        if (null != bookPO.getPrice()) {
            updateRequest.doc(XContentType.JSON, "price", bookPO.getPrice());
        }
        UpdateResponse updateResponse;
        try {
            updateResponse = elasticsearchClient.update(updateRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        log.info(updateResponse.toString());
    }

    /**
     * @param
     * @return org.elasticsearch.action.search.SearchResponse
     * @description 获取查询结果
     * @author zhou
     * @create 2022/1/4 15:01
     **/
    private SearchResponse getSearchResponse(SearchRequest searchRequest) {
        SearchResponse searchResponse;
        try {
            searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("ES服务连接异常");
            throw new RuntimeException("ES服务连接异常");
        }
        return searchResponse;
    }
}
