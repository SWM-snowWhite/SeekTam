package food.backend.search.controller;

import food.backend.search.dto.RelatedFoodDto;
import food.backend.search.service.FoodSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/foods/search")
@RequiredArgsConstructor
public class FoodController {

    private final FoodSearchService foodSearchService;

    @GetMapping("/syllable")
    public List<RelatedFoodDto> searchAllRelatedFood(@RequestParam String name) {
        return foodSearchService.getFoodByNameContaining(name);
    }

    @GetMapping("/detail")
    public List<Map<String, Object>> getFoodDetail(@RequestParam Long foodId) {
        return foodSearchService.getFoodDetailById(foodId);
    }

}
