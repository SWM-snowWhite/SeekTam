package food.backend.mall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisRepositoryTest {
    @Autowired
    private MallRedisRepository redisRepository;

    @Test
    void test() {
        RankInfo rankInfo = RankInfo.builder()
                .ranking(1)
                .foodKeyword("test")
                .build();

        // 저장
        redisRepository.save(rankInfo);

        // 조회
                redisRepository.findById("1");

        // 카운트
        redisRepository.count();

        // 삭제
        redisRepository.delete(rankInfo);
    }
}
