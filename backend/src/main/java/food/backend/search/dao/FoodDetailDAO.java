package food.backend.search.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class FoodDetailDAO {

    private final JdbcTemplate template;

    public List<Map<String, Object>> getFoodDataById(Long foodId) {
        String sql = "SELECT * FROM food_main WHERE food_id = ?";
        return template.queryForList(sql, foodId);
    }

}