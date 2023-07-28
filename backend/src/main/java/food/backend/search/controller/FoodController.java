package food.backend.search.controller;

import food.backend.search.dto.RelatedFoodDto;
import food.backend.search.dto.FoodInfoDTO;
import food.backend.search.service.FoodSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods/search")
@RequiredArgsConstructor
public class FoodController {

    private final FoodSearchService foodSearchService;

    @GetMapping("/{foodId}")
    public List<FoodInfoDTO> getFoodInfo(@PathVariable Long foodId) {
        return foodSearchService.getFoodInfoById(foodId);
    }

    @GetMapping("/syllable")
    public List<RelatedFoodDto> searchAllRelatedFood(@RequestParam String name) {
        return foodSearchService.getFoodByNameContaining(name);
    }

}
