package com.tyrival.elastic.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Objects;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/30
 * @Version: V1.0
 */
@Configuration
public class ElasticConfig {

    @Value("${spring.data.elasticsearch.cluster-nodes}")
    String[] clusterNodes;

    private static final int ADDRESS_LENGTH = 2;

    private static final String HTTP_SCHEME = "http";

    @Bean
    public RestHighLevelClient restClient() {
        HttpHost[] hosts = Arrays.stream(clusterNodes)
                .map(this::createHttpHost)
                .filter(Objects::nonNull)
                .toArray(HttpHost[]::new);
        RestHighLevelClient restClient = new RestHighLevelClient(RestClient.builder(hosts));
        return restClient;
    }

    private HttpHost createHttpHost(String s) {
        assert StringUtils.isNotEmpty(s);
        String[] address = s.split(":");
        if (address.length == ADDRESS_LENGTH) {
            String ip = address[0];
            int port = Integer.parseInt(address[1]);
            return new HttpHost(ip, port, HTTP_SCHEME);
        } else {
            return null;
        }
    }
}
