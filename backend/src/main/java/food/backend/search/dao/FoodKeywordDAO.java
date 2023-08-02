package food.backend.search.dao;

import food.backend.search.dto.RelatedFoodDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class FoodKeywordDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public FoodKeywordDAO(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<String> getFoodByNameContaining(String keyword) {

        final int LIMIT_NUM = 10;

        String sql = "SELECT food_id, food_name FROM food_main WHERE food_name LIKE :name limit :LIMIT_NUM";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", "%" + keyword + "%")
                .addValue("LIMIT_NUM", LIMIT_NUM);

        return jdbcTemplate.query(sql, params, foodRowMapper());

    }

    private RowMapper<String> foodRowMapper() {
        return (rs, rowNum) ->
                        rs.getString("food_name");


    }


}

