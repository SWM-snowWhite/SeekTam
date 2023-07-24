package food.backend.search.controller;

import food.backend.search.dto.FoodDTO;
import food.backend.search.service.FoodSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
