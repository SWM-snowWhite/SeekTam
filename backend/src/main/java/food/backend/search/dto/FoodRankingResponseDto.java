package food.backend.search.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Deprecated
@Getter
@NoArgsConstructor
public class FoodRankingResponseDto {
    private Long foodId;
    private String foodName;
    private int ranking;
    private int calories;
    private boolean liked;

    @Builder
    public FoodRankingResponseDto(Long foodId, String foodName, int ranking, int calories, boolean liked) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.ranking = ranking;
        this.calories = calories;
        this.liked = liked;
    }
}
