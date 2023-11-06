package food.backend.search.dao;

import food.backend.search.dto.ProductDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * ID : ST-C-120-J 작성자 : 임동훈(snowcrab382@naver.com) 버전 : 1.0.0 작성일 : 2023-11-06
 */
@Repository
@RequiredArgsConstructor
public class ProductDetailDao {

    private final JdbcTemplate jdbcTemplate;

    public ProductDetailDto getProductDetail(Integer foodId) {
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
}
