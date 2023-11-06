package food.backend.search.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;

/**
 * 컨트롤러에서 searchFoodListByNutrient 메소드의 리턴값으로 사용되는 객체<br> 식품의 고유번호, 식품명, 제조사명을 담고 있음
 *
 * @deprecated
 */
@Deprecated
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Builder
public class FoodListDto {

    private Long foodId;
    private String foodName;
    private String companyName;
}
