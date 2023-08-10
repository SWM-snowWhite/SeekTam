package food.backend.search.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;

/**
 * 컨트롤러에서 searchAllRelatedFood 메소드의 리턴값으로 사용되는 객체<br>
 * 식품명을 담고 있음
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Builder
public class RelatedFoodDto {

    private String foodName;

}

