package food.backend.search.dao;

import food.backend.search.dto.FoodDetailDto;
import food.backend.search.dto.FoodRankingResponseDto;
import food.backend.search.model.ViewsFood;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * ID : ST-C-110-J 작성자 : 임동훈(snowcrab382@naver.com) 버전 : 1.0.0 작성일 : 2023-10-20
 *
 * @deprecated
 */
@Deprecated // 2023-11-06
@Repository
@RequiredArgsConstructor
public class FoodDetailDao {

    @PersistenceContext
    private final EntityManager em;
    /**
     * JdbcTemplate을 사용해 DB에 접근
     *
     * @see JdbcTemplate
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * 음식의 고유번호를 통해 음식의 상세정보를 반환하는 메소드
     *
     * @param foodId 컨트롤러에서 쿼리파라미터로 전달받은 음식의 고유번호
     * @return 음식의 고유번호를 통해 검색된 음식의 상세정보를 FoodDetailDTO 객체로 반환
     */
    public FoodDetailDto getFoodDataById(Long foodId) {
        String sql = "SELECT * FROM food_main WHERE food_id = ?";
        return jdbcTemplate.queryForObject(sql, foodRowMapper(), foodId);
    }

    /**
     * DB에서 검색된 음식의 상세정보를 FoodDetailDTO 객체로 매핑해주는 메소드
     *
     * @return DB에서 검색된 음식의 상세정보를 FoodDetailDTO 객체로 매핑해주는 RowMapper
     * @see FoodDetailDto
     */
    private RowMapper<FoodDetailDto> foodRowMapper() {
        return (rs, rowNum) ->
                FoodDetailDto.builder()
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

    @Transactional
    public void increaseViewCount(Long foodId, Long memberId) {
        em.persist(ViewsFood.builder()
                .foodId(foodId)
                .memberId(memberId)
                .build());
    }

    private RowMapper<FoodRankingResponseDto> foodRankingRowMapper() {
        return (rs, rowNum) ->
                FoodRankingResponseDto.builder()
                        .foodId(rs.getLong("food_id"))
                        .foodName(rs.getString("food_name"))
                        .calories(rs.getInt("calories"))
                        .ranking(rs.getInt("ranking"))
                        .liked(rs.getBoolean("liked"))
                        .build();
    }

    public List<FoodRankingResponseDto> getFoodRanking(String email) {
        String sql =
                "SELECT * FROM (" +
                        "SELECT " +
                        "f.food_id, " +
                        "f.food_name, " +
                        "f.enerc AS calories, " +
                        "ROW_NUMBER() OVER (ORDER BY fc.count DESC, f.food_id) AS ranking, " +
                        "CASE " +
                        "WHEN (" +
                        "SELECT 1 " +
                        "FROM like_list ll " +
                        "WHERE " +
                        "f.food_id = ll.food_id " +
                        "AND ll.member_id = ?" +
                        ") IS NOT NULL THEN TRUE ELSE FALSE " +
                        "END AS liked " +
                        "FROM " +
                        "food_main f " +
                        "JOIN ( " +
                        "SELECT " +
                        "food_id, " +
                        "COUNT(food_id) AS count " +
                        "FROM " +
                        "views_food " +
                        "GROUP BY " +
                        "food_id " +
                        ") AS fc ON f.food_id = fc.food_id " +
                        ") AS ranked_food " +
                        "WHERE " +
                        "ranking <= 10 " +
                        "ORDER BY " +
                        "ranking";
        return jdbcTemplate.query(sql, foodRankingRowMapper(), email);
    }
}