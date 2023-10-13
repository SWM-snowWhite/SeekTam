package food.backend.mall.dao;

import food.backend.mall.RankInfo;
import food.backend.mall.dto.MallRankingResponseDto;
import food.backend.search.dto.FoodDetailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MallRdsDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    public List<RankInfo> getMallRanking() {
        String sql = "SELECT * FROM food_keyword_ranking ORDER BY ID DESC limit 10";
        return jdbcTemplate.query(sql, MalLRankingMapper());
    }
    private RowMapper<RankInfo> MalLRankingMapper() {
        return (rs, rowNum) ->
                RankInfo.builder()
                        .id(rs.getLong("id"))
                        .createdDate(rs.getTimestamp("created_date"))
                        .ranking(rs.getInt("ranking"))
                        .foodKeyword(rs.getString("keyword"))
                        .hits(rs.getInt("hits")).build();
    }
}

