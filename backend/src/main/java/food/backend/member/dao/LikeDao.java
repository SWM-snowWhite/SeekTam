package food.backend.member.dao;

import food.backend.member.request.LikeRequest;
import food.backend.member.response.LikeResponse;
import food.backend.search.dao.FoodDetailDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class LikeDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final FoodDetailDao foodDetailDao;
    public void pushLikeFood(LikeRequest likeRequest, String memberId) {
        Long foodId = likeRequest.getFoodId();
        String foodName = foodDetailDao.getFoodDataById(foodId).getFoodNm();

        log.info(foodId + " " + foodName + " " + memberId);
        String sql = "INSERT INTO like_list (food_id, member_id, food_name) values (:food_id, :member_id, :food_name)";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("food_id", foodId)
                .addValue("member_id", memberId)
                .addValue("food_name", foodName);

        namedParameterJdbcTemplate.update(sql,params);
    }

    public void unlikeFood(LikeRequest likeRequest, String memberId) {
        Long foodId = likeRequest.getFoodId();

        String sql = "DELETE FROM like_list where member_id = :member_id AND food_id = :food_id";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("member_id", memberId)
                .addValue("food_id", foodId);

        namedParameterJdbcTemplate.update(sql, params);
    }

    public List<LikeResponse> getLikeList(String memberId) {

        String sql = "SELECT * from like_list where member_id = :member_id";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("member_id", memberId);

        return namedParameterJdbcTemplate.query(sql, params, likeRowMapper());
    }

    public boolean checkLike(String memberId, Long foodId) {

        try {
            String sql = "SELECT * from like_list where member_id = :member_id and food_id = :food_id";

            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("member_id", memberId)
                    .addValue("food_id", foodId);

            namedParameterJdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> null);

            return true;
        } catch (Exception e) {
            log.info("error, {}", e);
            return false;
        }
    }

    private RowMapper<LikeResponse> likeRowMapper() {
        return (rs, rowNum) ->
                LikeResponse.builder()
                        .foodId(rs.getLong("food_id"))
                        .foodName(rs.getString("food_name"))
                        .build();

    }
}
