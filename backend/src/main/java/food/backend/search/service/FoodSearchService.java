package food.backend.search.service;

import food.backend.search.dao.FoodDetailDAO;
import food.backend.search.dto.RelatedFoodDto;
import food.backend.search.dao.FoodKeywordDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FoodSearchService {

    private final FoodKeywordDAO foodKeywordDAO;
    private final FoodDetailDAO foodDetailDAO;

    public List<RelatedFoodDto> getFoodByNameContaining(String name) {
        return foodKeywordDAO.getFoodByNameContaining(name);
    }

    public Map<String, Object> getFoodDetailById(String foodId) {
        return foodDetailDAO.getFoodDataById(foodId);
    }



}