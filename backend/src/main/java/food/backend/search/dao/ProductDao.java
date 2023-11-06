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


    private final ElasticsearchConfig elasticsearchConfig;

    public List<ProductDto> getProductByKeywordAndNutrient(KeywordAndNutrientEs params) {
        SearchRequest searchRequest = createRequest(params);

        SearchResponse searchResponse = getResponse(searchRequest);

        return getSearchResult(searchResponse);
    }

    private static SearchRequest createRequest(KeywordAndNutrientEs params) {
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery(FOOD_NAME_FIELD, params.getKeyword()));

        putNutrientCondition(FAT_FIELD, params.getFat(), params.getFatCon(), boolQueryBuilder);
        putNutrientCondition(PROTEIN_FIELD, params.getProtein(), params.getProteinCon(), boolQueryBuilder);
        putNutrientCondition(CARBOHYDRATE_FIELD, params.getCarbohydrate(), params.getCarbohydrateCon(),
                boolQueryBuilder);
        putNutrientCondition(CALORIE_FIELD, params.getCalorie(), params.getCalorieCon(), boolQueryBuilder);

        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.fetchSource(new String[]{FOOD_ID_FIELD, ProductDao.FOOD_NAME_FIELD, COMPANY_NAME_FIELD},
                null);
        searchSourceBuilder.size(100);
        searchRequest.source(searchSourceBuilder);
        return searchRequest;
    }

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

    private SearchResponse getResponse(SearchRequest searchRequest) {
        SearchResponse searchResponse = null;
        try {
            searchResponse = elasticsearchConfig.client().search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return searchResponse;
    }

    private static List<ProductDto> getSearchResult(SearchResponse searchResponse) {
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();

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
