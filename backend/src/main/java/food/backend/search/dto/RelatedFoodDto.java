package food.backend.search.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;

/**
 * ID : ST-C-110-J
 * 작성자 : 임동훈(snowcrab382@naver.com)
 * 버전 : 1.0.0
 * 작성일 : 2023-10-20
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Builder
public class RelatedFoodDto {

    private String foodName;

}

