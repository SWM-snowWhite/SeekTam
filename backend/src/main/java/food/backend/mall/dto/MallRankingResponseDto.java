package food.backend.mall.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MallRankingResponseDto {
    private Integer ranking;
    private String foodKeyword;
}
