package food.backend.search.dto;

import lombok.Builder;
import lombok.Data;

/**
 * ID : ST-C-120-J 작성자 : 임동훈(snowcrab382@naver.com) 버전 : 1.0.0 작성일 : 2023-11-06
 */
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
    private boolean liked;
}
