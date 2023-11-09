package food.backend.search.dao;

import food.backend.search.dto.FoodListDto;
import food.backend.search.model.KeywordAndNutrient;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * ID : ST-C-110-J 작성자 : 임동훈(snowcrab382@naver.com) 버전 : 1.0.0 작성일 : 2023-10-20
 *
 * @deprecated
 */
@Deprecated
@Repository
@RequiredArgsConstructor
public class FoodListDao {

    /**
     * JdbcTemplate에서 제공하는 NamedParameterJdbcTemplate을 사용해 DB 접근<br> NamedParameterJdbcTemplate는 쿼리문에 파라미터를 바인딩할 때 파라미터의
     * 이름을 사용할 수 있도록 해줌
     *
     * @see NamedParameterJdbcTemplate
     */
    private final NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * 키워드를 포함하는 음식 리스트에서 영양소 조건에 맞는 음식 리스트를 반환하는 메소드
     *
     * @param params 컨트롤러에서 쿼리파라미터로 전달받은 키워드와 영양소 조건을 담은 객체
     * @return 키워드를 포함하는 음식 리스트에서 영양소 조건에 맞는 음식의 foodId,foodName,companyName 리스트
     */
    public List<FoodListDto> getFoodListByNameContaining(KeywordAndNutrient params) {
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

        sql += " limit 10";
        return jdbcTemplate.query(sql, paramMap, foodRowMapper());
    }

    /**
     * 키워드를 포함하는 음식 리스트에서 영양소 조건에 맞는 음식 리스트를 반환하는 메소드
     *
     * @return 키워드를 포함하는 음식 리스트에서 영양소 조건에 맞는 음식의 foodId,foodName,companyName 리스트
     */
    private RowMapper<FoodListDto> foodRowMapper() {
        return (rs, rowNum) ->
                FoodListDto.builder()
                        .foodId(rs.getLong("food_id"))
                        .foodName(rs.getString("food_name"))
                        .companyName(rs.getString("company_name"))
                        .build();
    }
}