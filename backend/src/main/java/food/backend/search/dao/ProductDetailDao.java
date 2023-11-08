package food.backend.search.dao;

import food.backend.search.dto.FoodDetailDto;
import food.backend.search.dto.FoodRankingResponseDto;
import food.backend.search.dto.ProductDetailDto;
import food.backend.search.model.ViewsFood;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * ID : ST-C-120-J 작성자 : 임동훈(snowcrab382@naver.com) 버전 : 1.0.0 작성일 : 2023-11-06
 */
@Repository
@RequiredArgsConstructor
public class ProductDetailDao {

    @PersistenceContext
    private final EntityManager em;
    private final JdbcTemplate jdbcTemplate;
    public ProductDetailDto getProductDetail(Long foodId) {
        String sql = "SELECT * FROM products WHERE food_id = ?";
        return jdbcTemplate.queryForObject(sql, productRowMapper(), foodId);
    }

    private RowMapper<ProductDetailDto> productRowMapper() {
        return (rs, rowNum) ->
                ProductDetailDto.builder()
                        .foodId(rs.getInt("food_id"))
                        .foodCode(rs.getString("food_code"))
                        .foodName(rs.getString("food_name"))
                        .releasedYear(rs.getInt("released_year"))
                        .companyName(rs.getString("company_name"))
                        .mainCategory(rs.getString("main_category"))
                        .detailedCategory(rs.getString("detailed_category"))
                        .servingSize(rs.getString("serving_size"))
                        .servingUnit(rs.getString("serving_unit"))
                        .totalSizeG(rs.getDouble("total_size_g"))
                        .totalSizeMl(rs.getDouble("total_size_ml"))
                        .calorie(rs.getDouble("calorie"))
                        .protein(rs.getDouble("protein"))
                        .fat(rs.getDouble("fat"))
                        .carbohydrate(rs.getDouble("carbohydrate"))
                        .totalSugar(rs.getDouble("total_sugar"))
                        .salt(rs.getDouble("salt"))
                        .cholesterol(rs.getDouble("cholesterol"))
                        .totalSaturatedFat(rs.getDouble("total_saturated_fat"))
                        .transFat(rs.getDouble("trans_fat"))
                        .totalUnsaturatedFat(rs.getDouble("total_unsaturated_fat"))
                        .build();
    }

    @Transactional
    public void increaseViewCount(Long foodId, Long memberId) {
        em.persist(ViewsFood.builder()
                .foodId(foodId)
                .memberId(memberId)
                .build());
    }

    private RowMapper<FoodRankingResponseDto> foodRankingRowMapper() {
        return (rs, rowNum) ->
                FoodRankingResponseDto.builder()
                        .foodId(rs.getLong("food_id"))
                        .foodName(rs.getString("food_name"))
                        .calorie(rs.getInt("calorie"))
                        .ranking(rs.getInt("ranking"))
                        .liked(rs.getBoolean("liked"))
                        .build();
    }

    public List<FoodRankingResponseDto> getFoodRanking(String email) {
        String sql = "SELECT * FROM (" +
                "    SELECT " +
                "        p.food_id, " +
                "        p.food_name, " +
                "        p.calorie," +
                "        ROW_NUMBER() OVER (ORDER BY fc.count DESC, p.food_id) AS ranking, " +
                "        CASE " +
                "            WHEN (" +
                "                SELECT 1 " +
                "                FROM like_list ll " +
                "                WHERE " +
                "                    p.food_id = ll.food_id " +
                "                    AND ll.member_id = ?" +
                "            ) IS NOT NULL THEN TRUE ELSE FALSE " +
                "        END AS liked " +
                "    FROM " +
                "        products p " +
                "        JOIN ( " +
                "            SELECT " +
                "                food_id, " +
                "                COUNT(food_id) AS count " +
                "            FROM " +
                "                views_food " +
                "            GROUP BY " +
                "                food_id " +
                "            ORDER BY " +
                "                count DESC " +
                "        ) AS fc ON p.food_id = fc.food_id " +
                ") AS ranked_food " +
                "WHERE " +
                "    ranking <= 10 " +
                "ORDER BY " +
                "    ranking;";

        return jdbcTemplate.query(sql, foodRankingRowMapper(), email);
    }
}
