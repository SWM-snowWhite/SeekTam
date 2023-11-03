package food.backend.search.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDetailDto {

    private Integer foodId;
    private String foodCode;
    private Integer releasedYear;
    private String foodName;
    private String companyName;
    private String mainCategory;
    private String detailedCategory;
    private String servingSize;
    private String servingUnit;
    private Double totalSizeG;
    private Double totalSizeMl;
    private Double calorie;
    private Double carbohydrate;
    private Double protein;
    private Double totalSugar;
    private Double salt;
    private Double fat;
    private Double totalUnsaturatedFat;
    private Double totalSaturatedFat;
    private Double transFat;
    private Double cholesterol;
}
