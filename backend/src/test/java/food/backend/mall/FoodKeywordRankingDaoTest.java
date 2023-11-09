//package food.backend.mall;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import food.backend.mall.dao.MallRdsDao;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.when;
//
//@SpringJUnitConfig
//public class FoodKeywordRankingDaoTest {
//
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//    @InjectMocks
//    private MallRdsDao mallRdsDao;
//
//
//    public static List<RankInfo> createSampleRankInfos() {
//        List<RankInfo> rankInfos = new ArrayList<>();
//
//        for (int i = 1; i <= 10; i++) {
//            RankInfo rankInfo = new RankInfo();
//            rankInfo.setId((long) i);
//            rankInfo.setCreatedDate(new Date()); // Assuming you want the current date for all
//            rankInfo.setRanking(11 - i); // Assuming rankings in reverse order for the example
//            rankInfo.setFoodKeyword("Keyword " + i);
//            rankInfo.setHits(i * 10); // Assuming hits are 10, 20, ... for the example
//            rankInfos.add(rankInfo);
//        }
//
//        return rankInfos;
//    }
//
//    @DisplayName("쇼핑몰 상위 키워드 랭킹을 JDBC를 통해 조회한다.")
//    @Test
//    void getMallRankingTestWithJdbc() {
//
//        // given
//        String sql = "SELECT * FROM food_keyword_ranking ORDER BY ID DESC limit 10";
//        List<RankInfo> expectedRankings = createSampleRankInfos();
//
//        // when
//        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedRankings);
//
//        // then
//        List<RankInfo> actualRankings = mallRdsDao.getMallRanking();
//
//        assertThat(actualRankings).hasSize(10);
//        assertThat(actualRankings).isEqualTo(expectedRankings);
//    }
//
//    public RowMapper<RankInfo> MallRankingMapper() {
//        return (rs, rowNum) -> {
//            RankInfo rankInfo = new RankInfo();
//            rankInfo.setId(rs.getLong("id"));
//            rankInfo.setCreatedDate(rs.getTimestamp("created_date"));
//            rankInfo.setRanking(rs.getInt("ranking"));
//            rankInfo.setFoodKeyword(rs.getString("keyword"));
//            rankInfo.setHits(rs.getInt("hits"));
//            return rankInfo;
//        };
//    }
//}
