package food.backend.search.controller;

import food.backend.search.dto.FoodDTO;
import food.backend.search.dto.RelatedFoodDto;
import food.backend.search.service.FoodSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import food.backend.search.model.Food;
import food.backend.search.service.FoodSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/foods/search")
@RequiredArgsConstructor
public class FoodController {

//    private final FoodSearchService foodSearchService;

//    @GetMapping("/{foodId}")
//    public FoodDTO getFoodInfo(@PathVariable Long foodId) {
//        FoodDTO foodDTO = foodSearchService.getFoodInfoByFoodId(foodId);
//        return foodDTO;
//    }

    private final FoodSearchService foodSearchService;

    @GetMapping("/{foodId}")
    public List<Food> getFoodInfo(@PathVariable Long foodId) {
        return foodSearchService.getFoodInfoById(foodId);
    }
  
    @GetMapping("/syllable")
    public List<RelatedFoodDto> searchAllRelatedFood(@RequestParam String name) {
        return foodSearchService.getFoodByNameContaining(name);
    }
}
