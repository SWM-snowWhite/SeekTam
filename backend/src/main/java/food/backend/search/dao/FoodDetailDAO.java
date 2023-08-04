package food.backend.search.dao;

import food.backend.search.dto.FoodDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class FoodDetailDAO {

    private final JdbcTemplate jdbcTemplate;

    public FoodDetailDTO getFoodDataById(Long foodId) {
        String sql = "SELECT * FROM food_main WHERE food_id = ?";
        return jdbcTemplate.queryForObject(sql, foodRowMapper(), foodId);
    }

    private RowMapper<FoodDetailDTO> foodRowMapper() {
        return (rs, rowNum) ->
            FoodDetailDTO.builder()
                    .foodId(rs.getLong("food_id"))
                    .foodCd(rs.getString("food_code"))
                    .foodNm(rs.getString("food_name"))
                    .enerc(rs.getDouble("enerc"))
                    .water(rs.getDouble("water"))
                    .prot(rs.getDouble("prot"))
                    .fatce(rs.getDouble("fatce"))
                    .ash(rs.getDouble("ash"))
                    .chocdf(rs.getDouble("chocdf"))
                    .sugar(rs.getDouble("sugar"))
                    .fibtg(rs.getDouble("fibtg"))
                    .ca(rs.getDouble("ca"))
                    .fe(rs.getDouble("fe"))
                    .p(rs.getDouble("p"))
                    .k(rs.getDouble("k"))
                    .nat(rs.getDouble("nat"))
                    .vitaRae(rs.getDouble("vita_rae"))
                    .retol(rs.getDouble("retol"))
                    .cartb(rs.getDouble("cartb"))
                    .thia(rs.getDouble("thia"))
                    .ribf(rs.getDouble("ribf"))
                    .nia(rs.getDouble("nia"))
                    .vitc(rs.getDouble("vitc"))
                    .vitd(rs.getDouble("vitd"))
                    .chole(rs.getDouble("chole"))
                    .fasat(rs.getDouble("fasat"))
                    .fatrn(rs.getDouble("fatrn"))
                    .foodSize(rs.getString("food_size"))
                    .companyName(rs.getString("company_name"))
                    .build();
        }
    }
