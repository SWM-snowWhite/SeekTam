package food.backend.search.repository;


import food.backend.search.dto.RelatedFoodDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class FoodRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public FoodRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<RelatedFoodDto> getFoodByNameContaining(String name) {

        final int LIMIT_NUM = 10;

        String sql = "SELECT food_id, food_name FROM food_main WHERE food_name LIKE :name limit :LIMIT_NUM";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", "%" + name + "%")
                .addValue("LIMIT_NUM", LIMIT_NUM);

        return jdbcTemplate.query(sql, params, foodRowMapper());

    }

    private RowMapper<RelatedFoodDto> foodRowMapper() {
        return (rs, rowNum) ->
                RelatedFoodDto.builder()
                        .foodId(rs.getInt("food_id"))
                        .foodName(rs.getString("food_name"))
                        .build();

    }


}

