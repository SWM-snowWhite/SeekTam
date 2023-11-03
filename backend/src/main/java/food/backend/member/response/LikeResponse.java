package food.backend.member.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeResponse {

    private Long foodId;
    private String foodName;
    private String imageUrl;

}