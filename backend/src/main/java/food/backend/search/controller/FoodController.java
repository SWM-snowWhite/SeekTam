package food.backend.search.controller;

import food.backend.search.dto.FoodDTO;
import food.backend.search.dto.RelatedFoodDto;
import food.backend.search.service.FoodSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food/search")
@RequiredArgsConstructor
public class FoodController {

    private final FoodSearchService foodSearchService;

    @GetMapping("/{foodId}")
    public ResponseEntity<FoodDTO> getFoodInfo(@PathVariable Long foodId) {
        FoodDTO foodDTO = foodSearchService.getFoodInfoByFoodId(foodId);
        return ResponseEntity.ok(foodDTO);
    }

    @GetMapping("/syllable")
    public List<RelatedFoodDto> searchAllRelatedFood(@RequestParam String name) {
        return foodSearchService.getFoodByNameContaining(name);
    }

}
