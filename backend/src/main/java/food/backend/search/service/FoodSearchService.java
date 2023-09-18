package food.backend.search.service;

import food.backend.search.dao.FoodDetailDao;
import food.backend.search.dao.FoodListDao;
import food.backend.search.dto.FoodDetailDto;
import food.backend.search.dto.FoodListDto;
import food.backend.search.model.KeywordAndNutrient;
import food.backend.search.dao.FoodKeywordDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * ID : ST-C-110-J
 * 작성자 : 임동훈(snowcrab382@naver.com), 박주혁()
 * 버전 : 1.0.0
 * 작성일 : 2023-10-20
 */
@Service
@RequiredArgsConstructor
public class FoodSearchService {

    /**
     * 실제 DB에 접근해 데이터를 요청하는 DAO 클래스를 의존 주입
     */
    private final FoodKeywordDao foodKeywordDAO;
    private final FoodListDao foodListDAO;
    private final FoodDetailDao foodDetailDao;

    /**
     * 키워드를 포함하는 음식명을 검색해 상위 10개의 음식명을 리스트로 반환하는 메소드
     * @param keyword 컨트롤러에서 쿼리파라미터로 전달받은 키워드
     * @return 키워드를 포함하는 상위 10개의 음식명 리스트
     */
    public List<String> getFoodByNameContaining(String keyword) {
        return foodKeywordDAO.getFoodByNameContaining(keyword);
    }

    /**
     * 키워드를 포함하는 음식 리스트에서 영양소 조건에 맞는 음식 리스트를 반환하는 메소드
     * @param params 컨트롤러에서 쿼리파라미터로 전달받은 키워드와 영양소 조건을 담은 객체
     *               @see KeywordAndNutrient
     * @return 키워드를 포함하는 음식 리스트에서 영양소 조건에 맞는 음식의 foodId,foodName,companyName 리스트
     */
    public List<FoodListDto> getFoodListByNameContaining(KeywordAndNutrient params) {
        return foodListDAO.getFoodListByNameContaining(params);

    }

    /**
     * 음식의 고유번호를 통해 음식의 상세정보를 반환하는 메소드
     * @param foodId 컨트롤러에서 쿼리파라미터로 전달받은 음식의 고유번호
     * @return 음식의 고유번호를 통해 검색된 음식의 상세정보를 FoodDetailDTO 객체로 반환
     *         @see FoodDetailDto
     */
    public FoodDetailDto getFoodDetailById(Long foodId) {
        return foodDetailDao.getFoodDataById(foodId);
    }



}