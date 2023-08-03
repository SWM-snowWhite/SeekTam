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

        // ALTER TABLE food_test ADD FULLTEXT INDEX food_name (food_name) WITH PARSER ngram;
        // ngram_token_size = 2

        final int LIMIT_NUM = 10;

        String sql = "SELECT food_name FROM food_test WHERE MATCH(food_name) AGAINST(:substring IN boolean MODE)" +
                "limit :LIMIT_NUM;";

        keyword = keyword.length() == 1 ? keyword + "*" : keyword;

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("substring", keyword)
                .addValue("LIMIT_NUM", LIMIT_NUM);

        return jdbcTemplate.query(sql, params, foodRowMapper());

    }

    private RowMapper<String> foodRowMapper() {
        return (rs, rowNum) ->
                        rs.getString("food_name");


    }


}

