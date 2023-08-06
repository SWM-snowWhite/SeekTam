package food.backend.search.dao;

import food.backend.search.dto.FoodListDTO;
import food.backend.search.model.KeywordAndNutrient;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FoodListDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<FoodListDTO> getFoodListByNameContaining(KeywordAndNutrient params) {
        String sql = "SELECT food_id, food_name, company_name FROM food_main WHERE 1=1";

        MapSqlParameterSource paramMap = new MapSqlParameterSource()
                .addValue("food_name", "%" + params.getKeyword() + "%")
                .addValue("enerc", params.getEnerc())
                .addValue("chocdf", params.getChocdf())
                .addValue("prot", params.getProt())
                .addValue("fatce", params.getFatce());

        if (StringUtils.hasText(params.getKeyword())) {
            sql += " AND food_name LIKE :food_name";
        }

        if (StringUtils.hasText(params.getEnerc())) {
            sql += " AND enerc";
        }

        if (StringUtils.hasText(params.getEnercCon())) {
            if (params.getEnercCon().equals("1")) {
                sql += " >= :enerc";
            } else {
                sql += " <= :enerc";
            }
        }

        if (StringUtils.hasText(params.getChocdf())) {
            sql += " AND chocdf";
        }

        if (StringUtils.hasText(params.getChocdfCon())) {
            if (params.getChocdfCon().equals("1")) {
                sql += " >= :chocdf";
            } else {
                sql += " <= :chocdf";
            }
        }

        if (StringUtils.hasText(params.getProt())) {
            sql += " AND prot";
        }

        if (StringUtils.hasText(params.getProtCon())) {
            if (params.getProtCon().equals("1")) {
                sql += " >= :prot";
            } else {
                sql += " <= :prot";
            }
        }

        if (StringUtils.hasText(params.getFatce())) {
            sql += " AND fatce";
        }

        if (StringUtils.hasText(params.getFatceCon())) {
            if (params.getFatceCon().equals("1")) {
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
