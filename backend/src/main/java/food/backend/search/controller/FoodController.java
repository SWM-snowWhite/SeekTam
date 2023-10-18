package food.backend.search.controller;

import com.nimbusds.oauth2.sdk.util.JWTClaimsSetUtils;
import food.backend.oauth.common.jwt.JwtProvider;
import food.backend.search.dto.FoodDetailDto;
import food.backend.search.dto.FoodListDto;
import food.backend.search.model.KeywordAndNutrient;
import food.backend.search.service.FoodSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * "/foods/search" 로 시작되는 URI 요청을 처리하는 컨트롤러
 */
@RestController
@RequestMapping("/foods/search")
@RequiredArgsConstructor
@Slf4j
public class FoodController {
    private final JwtProvider jwtProvider;

    /**
     * 각 API 요청에 맞게 비즈니스 로직을 수행하여 결과를 반환하는 서비스 클래스를 의존 주입
     */
    private final FoodSearchService foodSearchService;

    /**
     * 키워드를 포함하는 음식명을 검색해 상위 10개의 음식명을 리스트로 반환하는 메소드<br>
     * 요청받는 쿼리파라미터 : keyword
     * @param keyword 쿼리파라미터로 전달받은 키워드
     * @return 키워드를 포함하는 상위 10개의 음식명 리스트
     */
    @GetMapping("/syllable")
    public List<String> searchAllRelatedFood(@RequestParam String keyword) {
        return foodSearchService.getFoodByNameContaining(keyword);
    }

    /**
     * 키워드를 포함하는 음식 리스트에서 영양소 조건에 맞는 음식 리스트를 반환하는 메소드<br>
     * 요청받는 쿼리파라미터 : keyword, enerc, enercCon, chocdf, chocdfCon, prot, protCon, fatce, fatceCon
     * @param params 쿼리파라미터로 전달받은 키워드와 영양소 조건을 담은 객체
     *               @see KeywordAndNutrient
     * @return 키워드를 포함하는 음식 리스트에서 영양소 조건에 맞는 음식의 foodId,foodName,companyName 리스트
     */
    @GetMapping
    public List<FoodListDto> searchFoodListByNutrient(@Validated @ModelAttribute KeywordAndNutrient params) {
        return foodSearchService.getFoodListByNameContaining(params);
    }

    /**
     * 음식의 고유번호를 통해 음식의 상세정보를 반환하는 메소드<br>
     * 요청받는 쿼리파라미터 : foodId
     * @param foodId 쿼리파라미터로 전달받은 음식의 고유번호
     * @return 음식의 상세정보를 담은 객체
     */
    @GetMapping("/detail")
    public FoodDetailDto getFoodDetail(@RequestParam Long foodId, HttpServletRequest request) {
        String token = request.getCookies()[0].getValue();

        log.info("token " + token);
        String email = jwtProvider.getEmailFromJwt(token);
        log.info("email " + email);
        return foodSearchService.getFoodDetailById(foodId, email);
    }

}
