package food.backend.search.service;

import food.backend.search.dao.FoodDetailDAO;
import food.backend.search.dao.FoodListDAO;
import food.backend.search.dto.FoodListDTO;
import food.backend.search.dto.RelatedFoodDto;
import food.backend.search.dao.FoodKeywordDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FoodSearchService {

    private final FoodKeywordDAO foodKeywordDAO;
    private final FoodListDAO foodListDAO;
    private final FoodDetailDAO foodDetailDAO;

    public List<String> getFoodByNameContaining(String keyword) {
        return foodKeywordDAO.getFoodByNameContaining(keyword);
    }

    public List<FoodListDTO> getFoodListByNameContaining(Map<String, String> params) {
        return foodListDAO.getFoodListByNameContaining(params);

    }

    public List<Map<String, Object>> getFoodDetailById(Long foodId) {
        return foodDetailDAO.getFoodDataById(foodId);
    }



}