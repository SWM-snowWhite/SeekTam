package food.backend.mall.service;

import food.backend.mall.RankInfo;
import food.backend.mall.dao.MallRdsDao;
import food.backend.mall.dto.MallRankingResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MallService {

    private final MallRdsDao mallRdsDao;
    @Cacheable(value = "mallRanking")
    public List<RankInfo> getMallRanking() {
        return mallRdsDao.getMallRanking();
    }
}
