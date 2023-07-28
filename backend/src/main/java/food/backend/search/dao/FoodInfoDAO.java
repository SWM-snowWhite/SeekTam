package food.backend.search.dao;

import food.backend.search.dto.FoodInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FoodInfoDAO {

    private final JdbcTemplate template;

    public List<FoodInfoDTO> getFoodById(Long foodId) {
        String sql =
                "SELECT fm.food_name, fm.report_no, fm.food_image, fm.purchase_link, fm.haccp_status, fmat.material_id, m.material_name, m.ewg_score, m.ibs_fructose, m.ibs_lactose, m.ibs_polyol, m.ibs_oligo " +
                "FROM food_main AS fm JOIN food_material AS fmat ON fm.food_id = fmat.food_id JOIN material AS m ON fmat.material_id = m.material_id WHERE fm.food_id = ?";

        RowMapper<FoodInfoDTO> FoodRowMapper = (rs, rowNum) -> {
            FoodInfoDTO foodInfoDTO = new FoodInfoDTO();
            foodInfoDTO.setFoodId(foodId);
            foodInfoDTO.setReportNo(rs.getLong("report_no"));
            foodInfoDTO.setFoodName(rs.getString("food_name"));
            foodInfoDTO.setFoodImage(rs.getString("food_image"));
            foodInfoDTO.setPurchaseLink(rs.getString("purchase_link"));
            foodInfoDTO.setHaccpStatus(rs.getInt("haccp_status"));
            foodInfoDTO.setMaterialName(rs.getString("material_name"));
            foodInfoDTO.setEwgScore(rs.getInt("ewg_score"));
            foodInfoDTO.setIbsFructose(rs.getInt("ibs_fructose"));
            foodInfoDTO.setIbsLactose(rs.getInt("ibs_lactose"));
            foodInfoDTO.setIbsPolyol(rs.getInt("ibs_polyol"));
            foodInfoDTO.setIbsOligo(rs.getInt("ibs_oligo"));
            return foodInfoDTO;
        };

        return template.query(sql, FoodRowMapper, foodId);
    }

}