package food.backend.search.dao;

import java.util.List;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * ID : ST-C-110-J 작성자 : 임동훈(snowcrab382@naver.com) 버전 : 1.0.0 작성일 : 2023-10-20
 *
 * @deprecated
 */
@Deprecated // 2023-11-06
@Repository
@Slf4j
public class FoodKeywordDao {

    /**
     * JdbcTemplate에서 제공하는 NamedParameterJdbcTemplate을 사용해 DB 접근<br> NamedParameterJdbcTemplate는 쿼리문에 파라미터를 바인딩할 때 파라미터의
     * 이름을 사용할 수 있도록 해줌
     */
    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * 생성자를 통해 의존 주입
     *
     * @param dataSource DB 접근을 위한 DataSource
     */
    public FoodKeywordDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * 키워드를 포함하는 음식 리스트를 반환하는 메소드
     *
     * @param keyword 컨트롤러에서 쿼리파라미터로 전달받은 키워드
     * @return 키워드를 포함하는 음식 리스트
     */
    public List<String> getFoodByNameContaining(String keyword) {
        // ALTER TABLE food_test ADD FULLTEXT INDEX food_name (food_name) WITH PARSER ngram;
        // ngram_token_size = 2

        final int LIMIT_NUM = 10;

        String sql = "SELECT food_name FROM food_main WHERE MATCH(food_name) AGAINST(:substring IN boolean MODE)" +
                "limit :LIMIT_NUM;";

        keyword = keyword.length() == 1 ? keyword + "*" : keyword;

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("substring", keyword)
                .addValue("LIMIT_NUM", LIMIT_NUM);

        return jdbcTemplate.query(sql, params, foodRowMapper());

    }

    /**
     * 키워드를 포함하는 음식명을 반환
     *
     * @return 키워드를 포함하는 음식명
     */
    private RowMapper<String> foodRowMapper() {
        return (rs, rowNum) ->
                rs.getString("food_name");
    }
}
