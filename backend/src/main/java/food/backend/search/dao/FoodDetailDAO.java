package food.backend.search.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class FoodDetailDAO {

    private final JdbcTemplate template;

    public Map<String, Object> getFoodDataById(String foodId) {
        String sql = "SELECT * FROM food_main WHERE food_id = ?";
        return template.queryForMap(sql, foodId);
    }

}