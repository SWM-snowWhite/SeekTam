package food.backend.search.dao;

import food.backend.search.config.ElasticsearchConfig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductKeywordDao {

    private final ElasticsearchConfig elasticsearchConfig;

    public List<String> getProductByKeyword(String keyword) {

        //ES 요청 쿼리를 담은 객체 생성
        SearchRequest searchRequest = new SearchRequest("products");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("food_name", keyword));
        searchSourceBuilder.size(10);
        searchSourceBuilder.fetchSource(new String[]{"food_name"}, null);
        searchRequest.source(searchSourceBuilder);

        //ES 요청 후 결과를 담은 객체 생성
        SearchResponse searchResponse = null;
        try {
            searchResponse = elasticsearchConfig.client().search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //ES 요청 결과 중 hit된 검색 결과만 추출
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();

        List<String> result = new ArrayList<>();
        for (SearchHit hit : searchHits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            result.add((String) sourceAsMap.get("food_name"));
        }

        return result;
    }
}
