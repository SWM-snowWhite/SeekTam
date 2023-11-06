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

/**
 * ID : ST-C-120-J 작성자 : 임동훈(snowcrab382@naver.com) 버전 : 1.0.0 작성일 : 2023-11-06
 */
@Repository
@RequiredArgsConstructor
public class ProductKeywordDao {

    /**
     * Elasticsearch 인덱스명
     */
    private static final String INDEX_NAME = "products";
    /**
     * Elasticsearch 식품명 필드명
     */
    public static final String FOOD_NAME_FIELD = "food_name";

    /**
     * ElasticsearchConfig 객체 주입(클라이언트 연결)
     */
    private final ElasticsearchConfig elasticsearchConfig;

    /**
     * ES 요청을 통해 키워드가 포함된 상품명 10개 반환
     *
     * @param keyword : 검색 키워드
     * @return List
     */
    public List<String> getProductByKeyword(String keyword) {
        // ES 요청 객체 생성
        SearchRequest searchRequest = createRequest(keyword);

        // ES 요청 후 결과를 담은 객체 생성
        SearchResponse searchResponse = getResponse(searchRequest);

        // ES 요청 결과에서 상품명 추출 후 반환
        return getSearchResult(searchResponse);
    }

    /**
     * ES 요청 결과에서 상품명 추출 후 반환
     *
     * @param searchResponse : ES 요청 후 결과를 담은 객체
     * @return List<>
     */
    private static List<String> getSearchResult(SearchResponse searchResponse) {
        // ES 요청 결과 중 hits 객체 추출
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();

        // 상품명을 담을 리스트 생성
        List<String> productByKeywords = new ArrayList<>();

        // 상품명 추출 후 리스트에 추가
        for (SearchHit hit : searchHits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            productByKeywords.add((String) sourceAsMap.get(FOOD_NAME_FIELD));
        }

        return productByKeywords;
    }

    /**
     * ES 요청 후 결과를 담은 객체 생성
     *
     * @param searchRequest : ES 요청 객체
     * @return SearchResponse
     */
    private SearchResponse getResponse(SearchRequest searchRequest) {
        // ES 요청 후 결과를 담을 객체 생성
        SearchResponse searchResponse = null;
        try {
            // ES 요청 후 결과를 저장
            searchResponse = elasticsearchConfig.client().search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return searchResponse;
    }

    /**
     * ES 요청 쿼리를 담은 객체 생성
     *
     * @param keyword : 검색 키워드
     * @return SearchRequest
     */
    private static SearchRequest createRequest(String keyword) {
        // ES 요청 객체 생성
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);

        // ES 요청 쿼리 생성
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        /*
          GET /products/_search { "query": { "match": { "food_name": "keyword" } }, "_source": ["food_name"] }
         */
        searchSourceBuilder.query(QueryBuilders.matchQuery(FOOD_NAME_FIELD, keyword));
        searchSourceBuilder.size(10);
        searchSourceBuilder.fetchSource(new String[]{FOOD_NAME_FIELD}, null);
        searchRequest.source(searchSourceBuilder);

        return searchRequest;
    }
}