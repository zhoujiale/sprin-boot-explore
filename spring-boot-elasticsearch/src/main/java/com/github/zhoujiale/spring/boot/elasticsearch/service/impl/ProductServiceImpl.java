package com.github.zhoujiale.spring.boot.elasticsearch.service.impl;

import com.github.zhoujiale.commons.util.page.PageVO;
import com.github.zhoujiale.spring.boot.elasticsearch.model.ProductPO;
import com.github.zhoujiale.spring.boot.elasticsearch.model.ProductQuery;
import com.github.zhoujiale.spring.boot.elasticsearch.model.ProductVO;
import com.github.zhoujiale.spring.boot.elasticsearch.repository.ProductRepository;
import com.github.zhoujiale.spring.boot.elasticsearch.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @name: ProductServiceImpl
 * @description:
 * @author: zhou
 * @create: 2021-12-11 19:07
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RestHighLevelClient elasticsearchClient;

    @Override
    public void add(ProductPO productPO) {
        productPO.setCreateDate(LocalDateTime.now());
        productPO.setUpdateDate(LocalDateTime.now());
        productPO = productRepository.save(productPO);
    }

    @Override
    public void delete(String productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public void modify(ProductPO productPO) {
        Optional<ProductPO> byId = productRepository.findById(productPO.getProductId());
        if (!byId.isPresent()) {
            log.warn("数据不存在");
            return;
        }
        ProductPO entity = byId.get();
        if (StringUtils.isNotBlank(productPO.getDescription())) {
            entity.setDescription(productPO.getDescription());
        }
        if (StringUtils.isNotBlank(productPO.getProductName())) {
            entity.setProductName(productPO.getProductName());
        }
        if (null != productPO.getPrice()) {
            entity.setPrice(productPO.getPrice());
        }
        entity.setUpdateDate(LocalDateTime.now());
        productRepository.save(entity);
    }

    @Override
    public PageVO<ProductVO> query(ProductQuery productQuery) {
        /**
         * 使用ElasticSearchRestTemplate进行查询
         *
         *
         *
         * **/
        //声明请求
        SearchRequest searchRequest = new SearchRequest("product");
        //构建查询
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //字段过滤
        String[] exclude = {"create_date", "update_date"};
        String[] include = {};
        searchSourceBuilder.fetchSource(include, exclude);
        //构造条件查询(布尔查询)
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        /**
         * 相当于sql中的and
         * boolQueryBuilder.must();
         * 相当于sql中的!=
         * boolQueryBuilder.mustNot()
         * 相当于sql中的or
         * boolQueryBuilder.should();
         **/
        //精确查询 类似sql的=
        boolQueryBuilder.must(QueryBuilders.termQuery("produce_name", productQuery.getKeyword()));
        //匹配查询
        boolQueryBuilder.must(QueryBuilders.matchQuery("product_name", productQuery.getKeyword()));
        //短语查询
        boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("product_name", productQuery.getKeyword()));
        //模糊
        boolQueryBuilder.must(QueryBuilders.fuzzyQuery("product_name", productQuery.getKeyword()).fuzziness(Fuzziness.ONE));
        //范围查询
        boolQueryBuilder.must(QueryBuilders.rangeQuery("price").from(productQuery.getMinPrice())
                .to(productQuery.getMaxPrice()));
        /**
         * 聚合
         * 平均数
         * AggregationBuilders.avg("price")
         * 计数
         * AggregationBuilders.count("product_id")
         * 总和
         * AggregationBuilders.sum("price")
         * **/
        searchSourceBuilder.aggregation(AggregationBuilders.avg("price"));
        //分组
        searchSourceBuilder.aggregation(AggregationBuilders.terms("category"));
        //排序
        searchSourceBuilder.sort("create_date", SortOrder.DESC);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = this.getSearchResponse(searchRequest);
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        return null;
    }

    @Override
    public PageVO<ProductVO> queryByElasticsearchClient(ProductQuery productQuery) {
        return null;
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
