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

/**
 * ID : ST-C-120-J 작성자 : 임동훈(snowcrab382@naver.com) 기능 : 상품 검색 컨트롤러 버전 : 1.0.0 작성일 : 2023-11-06
 */
@RestController
@RequestMapping("/foods/search")
@RequiredArgsConstructor
public class ProductSearchController {

    private final ProductSearchService productSearchService;

    /**
     * 키워드가 포함된 상품명 반환
     *
     * @param keyword : 검색 키워드
     * @return List
     */
    @GetMapping("/syllable")
    public List<String> searchProductByKeyword(String keyword) {
        return productSearchService.getProductByKeyword(keyword);
    }

    /**
     * 키워드가 포함되거나 키워드 포함 및 영양소 조건에 맞는 상품ID,상품명,제조사명 반환
     *
     * @param params : 검색 키워드, 칼로리, 칼로리 조건(0 or 1), 탄수화물, 탄수화물 조건, 단백질, 단백질 조건, 지방, 지방 조건
     * @return List
     * @see ProductDetailDto
     * @see KeywordAndNutrientEs
     */
    @GetMapping
    public List<ProductDto> searchProductByKeywordAndNutrient(@Validated @ModelAttribute KeywordAndNutrientEs params) {
        return productSearchService.getProductByKeywordAndNutrient(params);
    }

    /**
     * 상품의 모든 정보 반환
     *
     * @param foodId : 상품 ID
     * @return ProductDetailDto
     * @see ProductDetailDto
     */
    @GetMapping("/detail")
    public ProductDetailDto searchProductDetail(@RequestParam Integer foodId) {
        return productSearchService.getProductDetail(foodId);
    }
}
