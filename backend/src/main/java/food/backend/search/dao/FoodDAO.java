package food.backend.search.dao;

import food.backend.search.model.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class FoodDAO {

    private final JdbcTemplate template;

    public List<Food> getFoodById(Long foodId) {
        String sql =
                "SELECT fm.food_name, fm.report_no, fm.food_image, fm.purchase_link, fm.haccp_status, fmat.material_id, m.material_name, m.ewg_score, m.ibs_fructose, m.ibs_lactose, m.ibs_polyol, m.ibs_oligo " +
                "FROM food_main AS fm JOIN food_material AS fmat ON fm.food_id = fmat.food_id JOIN material AS m ON fmat.material_id = m.material_id WHERE fm.food_id = ?";

        RowMapper<Food> FoodRowMapper = (rs, rowNum) -> {
            Food food = new Food();
            food.setFoodId(foodId);
            food.setReportNo(rs.getLong("report_no"));
            food.setFoodName(rs.getString("food_name"));
            food.setFoodImage(rs.getString("food_image"));
            food.setPurchaseLink(rs.getString("purchase_link"));
            food.setHaccpStatus(rs.getInt("haccp_status"));
            food.setMaterialName(rs.getString("material_name"));
            food.setEwgScore(rs.getInt("ewg_score"));
            food.setIbsFructose(rs.getInt("ibs_fructose"));
            food.setIbsLactose(rs.getInt("ibs_lactose"));
            food.setIbsPolyol(rs.getInt("ibs_polyol"));
            food.setIbsOligo(rs.getInt("ibs_oligo"));
            return food;
        };

        return template.query(sql, FoodRowMapper, foodId);
    }

}