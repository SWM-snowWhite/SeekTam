package food.backend.search.dao;

import food.backend.search.dto.FoodListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class FoodListDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<FoodListDTO> getFoodListByNameContaining(Map<String, String> params) {
        String sql = "SELECT food_id, food_name, company_name FROM food_main WHERE 1=1";

        MapSqlParameterSource paramMap = new MapSqlParameterSource()
                .addValue("food_name", "%" + params.get("keyword") + "%")
                .addValue("enerc", params.get("kcal"))
                .addValue("enerc_con", params.get("kcal_con")) //없는 컬럼
                .addValue("chocdf", params.get("carb"))
                .addValue("chocdf_con", params.get("carb_con")) //없는 컬럼
                .addValue("prot", params.get("prot"))
                .addValue("prot_con", params.get("prot_con")) //없는 컬럼
                .addValue("fatce", params.get("fat"))
                .addValue("fatce_con", params.get("fat_con")); //없는 컬럼

        if (StringUtils.hasText(params.get("keyword"))) {
            sql += " AND food_name LIKE :food_name";
        }

        if (StringUtils.hasText(params.get("kcal"))) {
            sql += " AND enerc";
        }

        if (StringUtils.hasText(params.get("kcal_con"))) {
            if (params.get("kcal_con").equals("1")) {
                sql += " >= :enerc";
            } else {
                sql += " <= :enerc";
            }
        }

        if (StringUtils.hasText(params.get("carb"))) {
            sql += " AND chocdf";
        }

        if (StringUtils.hasText(params.get("carb_con"))) {
            if (params.get("carb_con").equals("1")) {
                sql += " >= :chocdf";
            } else {
                sql += " <= :chocdf";
            }
        }

        if (StringUtils.hasText(params.get("prot"))) {
            sql += " AND prot";
        }

        if (StringUtils.hasText(params.get("prot_con"))) {
            if (params.get("prot_con").equals("1")) {
                sql += " >= :prot";
            } else {
                sql += " <= :prot";
            }
        }

        if (StringUtils.hasText(params.get("fat"))) {
            sql += " AND fatce";
        }

        if (StringUtils.hasText(params.get("fat_con"))) {
            if (params.get("fat_con").equals("1")) {
                sql += " >= :fatce";
            } else {
                sql += " <= :fatce";
            }
        }

        return jdbcTemplate.query(sql, paramMap, foodRowMapper());
    }

    private RowMapper<FoodListDTO> foodRowMapper() {
        return (rs, rowNum) ->
                FoodListDTO.builder()
                        .foodId(rs.getLong("food_id"))
                        .foodName(rs.getString("food_name"))
                        .companyName(rs.getString("company_name"))
                        .build();
    }
}
