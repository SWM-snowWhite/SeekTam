package food.backend.search.dto;

import lombok.Data;

@Data
public class FoodInfoDTO {
    private Long foodId;
    private Long reportNo;
    private String foodName;
    private String foodImage;
    private String purchaseLink;
    private Integer haccpStatus;
    private String materialName;
    private Integer ewgScore;
    private Integer ibsFructose;
    private Integer ibsLactose;
    private Integer ibsPolyol;
    private Integer ibsOligo;

}
