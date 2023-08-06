package food.backend.search.controller;

import food.backend.search.dto.FoodDetailDTO;
import food.backend.search.dto.FoodListDTO;
import food.backend.search.model.KeywordAndNutrient;
import food.backend.search.service.FoodSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods/search")
@RequiredArgsConstructor
public class FoodController {

    private final FoodSearchService foodSearchService;

    @GetMapping("/syllable")
    public List<String> searchAllRelatedFood(@RequestParam String keyword) {
        return foodSearchService.getFoodByNameContaining(keyword);
    }

    @GetMapping
    public List<FoodListDTO> searchFoodListByNutrient(@Validated @ModelAttribute KeywordAndNutrient params) {
        return foodSearchService.getFoodListByNameContaining(params);
    }

    @GetMapping("/detail")
    public FoodDetailDTO getFoodDetail(@RequestParam Long foodId) {
        return foodSearchService.getFoodDetailById(foodId);
    }

}
