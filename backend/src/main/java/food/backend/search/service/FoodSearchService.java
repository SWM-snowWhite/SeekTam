package food.backend.search.service;

import food.backend.search.dto.FoodDTO;
import food.backend.search.dto.RelatedFoodDto;
import food.backend.search.entity.FoodMain;
import food.backend.search.entity.FoodMaterial;
import food.backend.search.repository.FoodMainRepository;
import food.backend.search.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FoodSearchService {

    private final FoodMainRepository foodMainRepository;
    private final FoodRepository foodRepository;
    public FoodDTO getFoodInfoByFoodId(Long foodId) {
        FoodMain foodMain = foodMainRepository.findByFoodId(foodId);

        Map<String,Integer> fodmapMaxScores = new LinkedHashMap<>();
        int maxFructoseScore = 0;
        int maxLactoseScore = 0;
        int maxPolyolScore = 0;
        int maxOligoScore = 0;

        Map<String, Integer> ewgTotalScores = new LinkedHashMap<String, Integer>(){
            {
                put("green", 0);
                put("orange", 0);
                put("red", 0);
                put("unknown", 0);
            }
        };

        List<String> materialNames = new ArrayList<>();
        List<Map<String, Object>> ewgScores = new ArrayList<>();
        List<Map<String, Object>> fodmapScores = new ArrayList<>();
        for (FoodMaterial foodMaterial : foodMain.getFoodMaterial()) {
            String materialName = foodMaterial.getMaterial().getMaterialName();

            Integer ewgScore = foodMaterial.getMaterial().getEwgScore();
            if (ewgScore == 0) {
                ewgTotalScores.put("unknown", ewgTotalScores.get("unknown") + 1);
            } else if (ewgScore >= 1 && ewgScore < 3) {
                ewgTotalScores.put("green", ewgTotalScores.get("green") + 1);
            } else if (ewgScore >= 3 && ewgScore < 7) {
                ewgTotalScores.put("orange", ewgTotalScores.get("orange") + 1);
            } else if (ewgScore >= 7 && ewgScore <= 10) {
                ewgTotalScores.put("red", ewgTotalScores.get("red") + 1);
            }

            Integer ibsFructoseScore = foodMaterial.getMaterial().getIbsFructose();
            Integer ibsLactoseScore = foodMaterial.getMaterial().getIbsLactose();
            Integer ibsPolyolScore = foodMaterial.getMaterial().getIbsPolyol();
            Integer ibsOligoScore = foodMaterial.getMaterial().getIbsOligo();
            maxFructoseScore = Math.max(maxFructoseScore, ibsFructoseScore);
            maxLactoseScore = Math.max(maxLactoseScore, ibsLactoseScore);
            maxPolyolScore = Math.max(maxPolyolScore, ibsPolyolScore);
            maxOligoScore = Math.max(maxOligoScore, ibsOligoScore);

            Map<String, Object> materialEwgScore = new LinkedHashMap<>();
            materialEwgScore.put("materialName", materialName);
            materialEwgScore.put("ewgScore", ewgScore);

            Map<String, Object> materialFodmapScore = new LinkedHashMap<>();
            materialFodmapScore.put("materialName", materialName);
            materialFodmapScore.put("ibsFructoseScore", ibsFructoseScore);
            materialFodmapScore.put("ibsLactoseScore", ibsLactoseScore);
            materialFodmapScore.put("ibsPolyolScore", ibsPolyolScore);
            materialFodmapScore.put("ibsOligoScore", ibsOligoScore);

            materialNames.add(materialName);
            ewgScores.add(materialEwgScore);
            fodmapScores.add(materialFodmapScore);
        }

        fodmapMaxScores.put("maxFructoseScore", maxFructoseScore);
        fodmapMaxScores.put("maxLactoseScore", maxLactoseScore);
        fodmapMaxScores.put("maxPolyolScore", maxPolyolScore);
        fodmapMaxScores.put("maxOligoScore", maxOligoScore);

        return FoodDTO.builder()
                .foodId(foodMain.getFoodId())
                .reportNo(foodMain.getReportNo())
                .foodName(foodMain.getFoodName())
                .foodImage(foodMain.getFoodImage())
                .purchaseLink(foodMain.getPurchaseLink())
                .haccpStatus(foodMain.getHaccpStatus())
                .materialNameList(materialNames)
                .ewgScoreList(ewgScores)
                .ewgTotalScoreList(ewgTotalScores)
                .fodmapScoreList(fodmapScores)
                .fodmapMaxScoreList(fodmapMaxScores)
                .build();
    }

    public List<RelatedFoodDto> getFoodByNameContaining(String name) {
        return foodRepository.getFoodByNameContaining(name);
    }



}