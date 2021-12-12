package com.zjl.spring_boot_elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @name: ElasticSearchConfiguration
 * @description: elasticsearch 配置
 * @author: zhou
 * @create: 2021-12-11 14:48
 */
@Configuration
public class ElasticSearchConfiguration extends AbstractElasticsearchConfiguration {

    @Value("${spring.elasticsearch.rest.uris}")
    private String uris;

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        System.out.println(uris);
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(uris)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
