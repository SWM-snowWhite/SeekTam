package food.backend.search.service;

import food.backend.search.dao.FoodInfoDAO;
import food.backend.search.dto.RelatedFoodDto;
import food.backend.search.dto.FoodInfoDTO;
import food.backend.search.dao.FoodKeywordDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FoodSearchService {

    private final FoodKeywordDAO foodKeywordDAO;
    private final FoodInfoDAO foodInfoDAO;

    public List<FoodInfoDTO> getFoodInfoById(Long foodId) {
        return foodInfoDAO.getFoodById(foodId);
    }

    public List<RelatedFoodDto> getFoodByNameContaining(String name) {
        return foodKeywordDAO.getFoodByNameContaining(name);
    }



}