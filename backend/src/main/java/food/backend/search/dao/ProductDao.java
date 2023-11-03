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

    private final ElasticsearchConfig elasticsearchConfig;

    public List<ProductDto> getProductByKeywordAndNutrient(KeywordAndNutrientEs params) {
        SearchRequest searchRequest = new SearchRequest("products");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("food_name", params.getKeyword()));

        if (StringUtils.hasText(params.getCalorie())) {
            RangeQueryBuilder calorieRange = QueryBuilders.rangeQuery("calorie");
            if (params.getCalorieCon().equals("0")) {
                calorieRange.lte(params.getCalorie());
            }
            if (params.getCalorieCon().equals("1")) {
                calorieRange.gte(params.getCalorie());
            }
            boolQueryBuilder.must(calorieRange);
        }

        if (StringUtils.hasText(params.getCarbohydrate())) {
            RangeQueryBuilder carbohydrateRange = QueryBuilders.rangeQuery("carbohydrate");
            if (params.getCarbohydrateCon().equals("0")) {
                carbohydrateRange.lte(params.getCarbohydrate());
            }
            if (params.getCarbohydrateCon().equals("1")) {
                carbohydrateRange.gte(params.getCarbohydrate());
            }
            boolQueryBuilder.must(carbohydrateRange);
        }

        if (StringUtils.hasText(params.getProtein())) {
            RangeQueryBuilder proteinRange = QueryBuilders.rangeQuery("protein");
            if (params.getProteinCon().equals("0")) {
                proteinRange.lte(params.getProtein());
            }
            if (params.getProteinCon().equals("1")) {
                proteinRange.gte(params.getProtein());
            }
            boolQueryBuilder.must(proteinRange);
        }

        if (StringUtils.hasText(params.getFat())) {
            RangeQueryBuilder fatRange = QueryBuilders.rangeQuery("fat");
            if (params.getFatCon().equals("0")) {
                fatRange.lte(params.getFat());
            }
            if (params.getFatCon().equals("1")) {
                fatRange.gte(params.getFat());
            }
            boolQueryBuilder.must(fatRange);
        }

        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.fetchSource(new String[]{"food_id", "food_name", "company_name"}, null);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = null;
        try {
            searchResponse = elasticsearchConfig.client().search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();

        List<ProductDto> result = new ArrayList<>();
        for (SearchHit hit : searchHits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            result.add(ProductDto.builder()
                    .foodId((Integer) sourceAsMap.get("food_id"))
                    .foodName((String) sourceAsMap.get("food_name"))
                    .companyName((String) sourceAsMap.get("company_name"))
                    .build());
        }

        return result;
    }
}
