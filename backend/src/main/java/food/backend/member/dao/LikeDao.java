package food.backend.member.dao;

import food.backend.member.request.LikeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeDao {

    private final JdbcTemplate jdbcTemplate;
    public void pushLikeFood(LikeRequest likeRequest, String memberId) {
        Long foodId = likeRequest.getFoodId();

        String sql = "INSERT INTO like_list (member_id, food_id) values (?, ?)";

        jdbcTemplate.update(sql, memberId, foodId);
    }

    public void unlikeFood(LikeRequest likeRequest, String memberId) {
        Long foodId = likeRequest.getFoodId();

        String sql = "DELETE FROM like_list where member_id = ? AND food_id = ?";

        jdbcTemplate.update(sql, memberId, foodId);
    }
}
