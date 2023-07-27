package food.backend.search.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Food {
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
