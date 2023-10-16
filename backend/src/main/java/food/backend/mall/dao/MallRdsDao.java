package food.backend.mall.dao;

import food.backend.mall.RankInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MallRdsDao extends MallRepository{
    private final JdbcTemplate jdbcTemplate;

    public List<RankInfo> getMallRanking() {
        String sql = "SELECT * FROM food_keyword_ranking ORDER BY ID DESC limit 10";
        return jdbcTemplate.query(sql, MalLRankingMapper());
    }
    private RowMapper<RankInfo> MalLRankingMapper() {
            return (rs, rowNum) -> {
                RankInfo rankInfo = new RankInfo();
                rankInfo.setId(rs.getLong("id"));
                rankInfo.setCreatedDate(rs.getTimestamp("created_date"));
                rankInfo.setRanking(rs.getInt("ranking"));
                rankInfo.setFoodKeyword(rs.getString("keyword"));
                rankInfo.setHits(rs.getInt("hits"));
                return rankInfo;
        };
    }

}

