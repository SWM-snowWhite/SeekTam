package food.backend.search.controller;


import food.backend.member.common.Authenticated;
import food.backend.member.request.MemberDto;
import food.backend.search.dto.FoodRankingResponseDto;
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
     * 키워드를 입력받고, 해당 키워드가 포함되는 상품명을 최대 10개 String 리스트로 반환합니다
     *
     * @param keyword : 검색 키워드
     * @return List
     */
    @GetMapping("/syllable")
    public List<String> searchProductByKeyword(String keyword) {
        return productSearchService.getProductByKeyword(keyword);
    }

    /**
     * 키워드만 입력받거나, 키워드 및 각각의 영양소 성분함량조건을 입력받아 조건에 부합하는 상품의 상품ID,상품명,제조사명을 최대 100개 반환합니다
     *
     * @param params : 검색 키워드, 칼로리, 칼로리 조건(0 or 1), 탄수화물, 탄수화물 조건, 단백질, 단백질 조건, 지방, 지방 조건
     * @return List
     * @see ProductDto
     * @see KeywordAndNutrientEs
     */
    @GetMapping
    public List<ProductDto> searchProductByKeywordAndNutrient(@Validated @ModelAttribute KeywordAndNutrientEs params) {
        return productSearchService.getProductByKeywordAndNutrient(params);
    }

    /**
     * 상품의 ID를 입력받아 해당 ID를 가지는 상품의 모든 정보(식품명,제조사명,각 영양성분 함량 등)을 반환합니다
     *
     * @param foodId : 상품 ID
     * @return ProductDetailDto
     * @see ProductDetailDto
     */
    @GetMapping("/detail")
    public ProductDetailDto searchProductDetail(@RequestParam Long foodId, @Authenticated MemberDto memberDto) {
        return productSearchService.getProductDetail(foodId, memberDto.getEmail());
    }

    @GetMapping("/ranking")
    public List<FoodRankingResponseDto> getFoodRanking(@Authenticated MemberDto memberDto) {
        return productSearchService.getFoodRanking(memberDto.getEmail());
    }
}
