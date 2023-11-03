package food.backend.search.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDto {

    private Integer foodId;
    private String foodName;
    private String companyName;
}
