package food.backend.search.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Builder
@Getter
public class FoodDTO {
    private Long foodId;
    private Long reportNo;
    private String foodName;
    private String foodImage;
    private String purchaseLink;
    private int haccpStatus;
    private List<String> materialNameList;
    private Map<String,Integer> ewgTotalScoreList;
    private List<Map<String, Object>> ewgScoreList;
//    private Map<String,Integer> iarcTotalScore;
//    private Map<String,Integer> iarcScoreList;
//    private List<String> allergyNameList;
    private Map<String,Integer> fodmapMaxScoreList;
    private List<Map<String, Object>> fodmapScoreList;

}
