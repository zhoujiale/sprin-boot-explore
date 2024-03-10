package com.github.zhoujiale.spring.boot.elasticsearch.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.NodeSelector;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

import java.util.Base64;

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

    @Value("${spring.elasticsearch.rest.username}")
    private String username;

    @Value("${spring.elasticsearch.rest.password}")
    private String password;


    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        System.out.println(uris);
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(uris)
                .withBasicAuth(username, password)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    public RestClient restLowLevelClient() {
        String[] split = uris.split(":");
        String raw = username + ":" + password;
        String encodeToString = Base64.getEncoder().encodeToString(raw.getBytes());
        //Header[] headers = {new BasicHeader("Authorization", "Basic " + encodeToString)};
        final BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
        basicCredentialsProvider.setCredentials(AuthScope.ANY,new UsernamePasswordCredentials(username,password));
        RestClient restClient = RestClient.builder(
                new HttpHost(split[0], Integer.parseInt(split[1]), "http")
        )
                //.setDefaultHeaders(headers)
                .setFailureListener(new RestClient.FailureListener(){
                    @Override
                    public void onFailure(Node node) {
                        super.onFailure(node);
                        System.out.println(node.toString());
                    }
                })
                .setRequestConfigCallback(builder -> builder.setSocketTimeout(10000).setConnectTimeout(5000))
                .setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultCredentialsProvider(basicCredentialsProvider))
                //跳过主节点
                .setNodeSelector(NodeSelector.SKIP_DEDICATED_MASTERS)
                .build();
        return restClient;
    }
}
