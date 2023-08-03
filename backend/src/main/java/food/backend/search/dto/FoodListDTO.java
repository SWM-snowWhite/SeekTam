package food.backend.search.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Builder
public class FoodListDTO {

    private Long foodId;
    private String foodName;
    private String companyName;
}
