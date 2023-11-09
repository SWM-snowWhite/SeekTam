//
//import food.backend.mall.dao.MallRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class RedisRepositoryTest {
//    @Autowired
//    private MallRepository redisRepository;
//
//    @DisplayName("RedisRepository 테스트")
//    @Test
//    void test() {
//        RankInfo rankInfo = new RankInfo();
//        rankInfo.setRanking(1);
//        rankInfo.setHits(1);
//        rankInfo.setFoodKeyword("test");
//
//        // 저장
//        redisRepository.save(rankInfo);
//
//        // 조회
//        redisRepository.findById(1);
//
//        // 카운트
//        redisRepository.count();
//
//        // 삭제
//        redisRepository.delete(rankInfo);
//    }
////
////    @DisplayName()
////    @Test
////    void queryDSL
//}
