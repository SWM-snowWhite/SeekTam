package food.backend.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * ID : ST-C-120-J 작성자 : 임동훈(snowcrab382@naver.com) 기능 : Elasticsearch에 클라이언트 요청 버전 : 1.0.0 작성일 : 2023-11-06
 */
@Configuration
public class ElasticsearchConfig {

    /**
     * Elasticsearch 호스트
     */
    @Value("${spring.elasticsearch.host}")
    private String host;

    /**
     * Elasticsearch 포트
     */
    @Value("${spring.elasticsearch.port}")
    private int port;

    /**
     * Elasticsearch 클라이언트 요청
     *
     * @return RestHighLevelClient
     */
    public RestHighLevelClient client() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(host, port, "http")));
    }
}
