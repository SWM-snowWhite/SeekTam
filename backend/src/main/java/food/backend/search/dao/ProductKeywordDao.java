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

    private static final String INDEX_NAME = "products";
    public static final String FOOD_NAME_FIELD = "food_name";

    private final ElasticsearchConfig elasticsearchConfig;

    public List<String> getProductByKeyword(String keyword) {
        SearchRequest searchRequest = createRequest(keyword);

        SearchResponse searchResponse = getResponse(searchRequest);

        return getSearchResult(searchResponse);
    }

    private static List<String> getSearchResult(SearchResponse searchResponse) {
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();

        List<String> productByKeywords = new ArrayList<>();
        for (SearchHit hit : searchHits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            productByKeywords.add((String) sourceAsMap.get(FOOD_NAME_FIELD));
        }

        return productByKeywords;
    }

    /**
     * ES 요청 후 결과를 담은 객체 생성
     *
     * @param searchRequest
     * @return SearchResponse
     */
    private SearchResponse getResponse(SearchRequest searchRequest) {
        SearchResponse searchResponse = null;
        try {
            searchResponse = elasticsearchConfig.client().search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return searchResponse;
    }

    /**
     * ES 요청 쿼리를 담은 객체 생성
     *
     * @param keyword
     * @return SearchRequest
     */
    private static SearchRequest createRequest(String keyword) {
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery(FOOD_NAME_FIELD, keyword));
        searchSourceBuilder.size(10);
        searchSourceBuilder.fetchSource(new String[]{FOOD_NAME_FIELD}, null);
        searchRequest.source(searchSourceBuilder);

        return searchRequest;
    }
}