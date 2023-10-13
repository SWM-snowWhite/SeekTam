package food.backend.mall.controller;

import food.backend.mall.RankInfo;
import food.backend.mall.dto.MallRankingResponseDto;
import food.backend.mall.service.MallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mall")
@RequiredArgsConstructor
public class MallController {
    private final MallService mallService;

    @GetMapping("/ranking")
    public ResponseEntity<List<RankInfo>> getMallRanking() {
        List<RankInfo> dtoResult = mallService.getMallRanking();

        return ResponseEntity.ok().body(dtoResult);
    }
}
