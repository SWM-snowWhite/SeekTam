package food.backend.search.controller;


import food.backend.search.dto.ProductDetailDto;
import food.backend.search.dto.ProductDto;
import food.backend.search.model.KeywordAndNutrientEs;
import food.backend.search.service.ProductSearchService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foods/search")
@RequiredArgsConstructor
public class ProductSearchController {

    private final ProductSearchService productSearchService;

    @GetMapping("/syllable")
    public List<String> searchProductByKeyword(String keyword) {
        return productSearchService.getProductByKeyword(keyword);
    }

    @GetMapping
    public List<ProductDto> searchProductByKeywordAndNutrient(@Validated @ModelAttribute KeywordAndNutrientEs params) {
        return productSearchService.getProductByKeywordAndNutrient(params);
    }

    @GetMapping("/detail")
    public ProductDetailDto getProductDetail(@RequestParam Integer foodId) {
        return productSearchService.getProductDetail(foodId);
    }
}
