package food.backend.search.dao;


import food.backend.search.config.ElasticsearchConfig;
import food.backend.search.dto.ProductDto;
import food.backend.search.model.KeywordAndNutrientEs;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * ID : ST-C-120-J 작성자 : 임동훈(snowcrab382@naver.com) 버전 : 1.0.0 작성일 : 2023-11-06
 */
@Repository
@RequiredArgsConstructor
public class ProductDao {

    public static final String INDEX_NAME = "products";

    public static final String FOOD_ID_FIELD = "food_id";
    public static final String FOOD_NAME_FIELD = "food_name";
    public static final String COMPANY_NAME_FIELD = "company_name";

    public static final String CALORIE_FIELD = "calorie";
    public static final String CARBOHYDRATE_FIELD = "carbohydrate";
    public static final String PROTEIN_FIELD = "protein";
    public static final String FAT_FIELD = "fat";

    public static final String CONDITION_BELOW = "0";
    public static final String CONDITION_ABOVE = "1";


    /**
     * ElasticsearchConfig 객체 주입(클라이언트 연결)
     */
    private final ElasticsearchConfig elasticsearchConfig;

    /**
     * 키워드가 포함되거나 키워드 포함 및 영양소 조건에 맞는 상품ID,상품명,제조사명 반환
     *
     * @param params : 검색 키워드, 칼로리, 칼로리 조건(0 or 1), 탄수화물, 탄수화물 조건, 단백질, 단백질 조건, 지방, 지방 조건
     * @return List
     * @see ProductDto
     * @see KeywordAndNutrientEs
     */
    public List<ProductDto> getProductByKeywordAndNutrient(KeywordAndNutrientEs params) {
        // ES 요청 객체 생성
        SearchRequest searchRequest = createRequest(params);

        // ES 요청 후 결과를 담은 객체 생성
        SearchResponse searchResponse = getResponse(searchRequest);

        // ES 요청 결과에서 상품ID,상품명,제조사명 추출 후 반환
        return getSearchResult(searchResponse);
    }

    /**
     * ES 요청 쿼리를 담은 객체 생성
     *
     * @param params : 검색 키워드, 칼로리, 칼로리 조건(0 or 1), 탄수화물, 탄수화물 조건, 단백질, 단백질 조건, 지방, 지방 조건
     * @return SearchRequest
     */
    private static SearchRequest createRequest(KeywordAndNutrientEs params) {
        // ES 요청 객체 생성
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);

        // ES 요청 쿼리를 담을 객체 생성
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        /*
          POST /products/_search
          {
            "query": {
              "bool": {
                "must": [
                  {
                    "match": {
                      "food_name": "keyword"
                    }
                  },
                  {
                    "range": {
                      "nutrientFieldName1": {
                        "gte(nutrientCondition = 1)" or "lte(nutrientCondition = 0)" : nutrient
                      }
                    }
                  },
                  {
                    "range": {
                      "nutrientFieldName2": {
                        "gte(nutrientCondition = 1)" or "lte(nutrientCondition = 0)" : nutrient
                      }
                    }
                  }
                ]
              }
            }
          }
         */
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery(FOOD_NAME_FIELD, params.getKeyword()));

        // 영양소 조건이 있는 경우 쿼리에 추가
        putNutrientCondition(FAT_FIELD, params.getFat(), params.getFatCon(), boolQueryBuilder);
        putNutrientCondition(PROTEIN_FIELD, params.getProtein(), params.getProteinCon(), boolQueryBuilder);
        putNutrientCondition(CARBOHYDRATE_FIELD, params.getCarbohydrate(), params.getCarbohydrateCon(),
                boolQueryBuilder);
        putNutrientCondition(CALORIE_FIELD, params.getCalorie(), params.getCalorieCon(), boolQueryBuilder);

        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.fetchSource(new String[]{FOOD_ID_FIELD, ProductDao.FOOD_NAME_FIELD, COMPANY_NAME_FIELD},
                null);
        searchRequest.source(searchSourceBuilder);
        return searchRequest;
    }

    /**
     * 영양소 조건이 있는 경우 쿼리에 추가
     *
     * @param fieldName         : 영양소 필드명
     * @param nutrient          : 영양소 값
     * @param nutrientCondition : 영양소 조건(0 : 이하, 1 : 이상)
     * @param boolQueryBuilder  : 쿼리를 담을 객체
     */
    private static void putNutrientCondition(String fieldName, String nutrient, String nutrientCondition,
                                             BoolQueryBuilder boolQueryBuilder) {
        if (StringUtils.hasText(nutrient)) {
            RangeQueryBuilder nutrientRange = QueryBuilders.rangeQuery(fieldName);
            if (nutrientCondition.equals(CONDITION_BELOW)) {
                nutrientRange.lte(nutrient);
            }
            if (nutrientCondition.equals(CONDITION_ABOVE)) {
                nutrientRange.gte(nutrient);
            }
            boolQueryBuilder.must(nutrientRange);
        }
    }

    /**
     * ES 요청 후 결과를 담은 객체 생성
     *
     * @param searchRequest : ES 요청 객체
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
     * ES 요청 결과에서 상품ID,상품명,제조사명 추출 후 반환
     *
     * @param searchResponse : ES 요청 후 결과를 담은 객체
     * @return List<ProductDto>
     */
    private static List<ProductDto> getSearchResult(SearchResponse searchResponse) {
        // ES 요청 결과 중 hits 객체 추출
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();

        // 상품ID,상품명,제조사명을 담을 리스트 생성
        List<ProductDto> result = new ArrayList<>();
        for (SearchHit hit : searchHits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            result.add(ProductDto.builder()
                    .foodId((Integer) sourceAsMap.get(FOOD_ID_FIELD))
                    .foodName((String) sourceAsMap.get(FOOD_NAME_FIELD))
                    .companyName((String) sourceAsMap.get(COMPANY_NAME_FIELD))
                    .build());
        }
        return result;
    }
}
