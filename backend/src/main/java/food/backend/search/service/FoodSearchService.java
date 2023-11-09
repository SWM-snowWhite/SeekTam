package food.backend.search.service;

import food.backend.member.Member;
import food.backend.member.MemberRepository;
import food.backend.search.dao.FoodDetailDao;
import food.backend.search.dao.FoodKeywordDao;
import food.backend.search.dao.FoodListDao;
import food.backend.search.dto.FoodDetailDto;
import food.backend.search.dto.FoodListDto;
import food.backend.search.dto.FoodRankingResponseDto;
import food.backend.search.model.KeywordAndNutrient;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ID : ST-C-110-J 작성자 : 임동훈(snowcrab382@naver.com), 박주혁() 버전 : 1.0.0 작성일 : 2023-10-20
 *
 * @deprecated
 */
@Deprecated
@Service
@RequiredArgsConstructor
public class FoodSearchService {

    /**
     * 실제 DB에 접근해 데이터를 요청하는 DAO 클래스를 의존 주입
     */
    private final FoodKeywordDao foodKeywordDAO;
    private final FoodListDao foodListDAO;
    private final FoodDetailDao foodDetailDao;
    private final MemberRepository memberRepository;

    /**
     * 키워드를 포함하는 음식명을 검색해 상위 10개의 음식명을 리스트로 반환하는 메소드
     *
     * @param keyword 컨트롤러에서 쿼리파라미터로 전달받은 키워드
     * @return 키워드를 포함하는 상위 10개의 음식명 리스트
     */
    public List<String> getFoodByNameContaining(String keyword) {
        return foodKeywordDAO.getFoodByNameContaining(keyword);
    }

    /**
     * 키워드를 포함하는 음식 리스트에서 영양소 조건에 맞는 음식 리스트를 반환하는 메소드
     *
     * @param params 컨트롤러에서 쿼리파라미터로 전달받은 키워드와 영양소 조건을 담은 객체
     * @return 키워드를 포함하는 음식 리스트에서 영양소 조건에 맞는 음식의 foodId,foodName,companyName 리스트
     * @see KeywordAndNutrient
     */
    public List<FoodListDto> getFoodListByNameContaining(KeywordAndNutrient params) {
        return foodListDAO.getFoodListByNameContaining(params);

    }

    /**
     * 음식의 고유번호를 통해 음식의 상세정보를 반환하는 메소드
     *
     * @param foodId 컨트롤러에서 쿼리파라미터로 전달받은 음식의 고유번호
     * @param email  컨트롤러에서 헤더에 담긴 사용자의 이메일
     * @return 음식의 고유번호를 통해 검색된 음식의 상세정보를 FoodDetailDTO 객체로 반환
     * @see FoodDetailDto
     */
    public FoodDetailDto getFoodDetailById(Long foodId, String email) {
        increaseViewCount(foodId, email);
        return foodDetailDao.getFoodDataById(foodId);
    }

    private void increaseViewCount(Long foodId, String email) {
        Optional<Member> memberId = memberRepository.findByEmail(email);
        if (memberId != null) {
            foodDetailDao.increaseViewCount(foodId, memberId.get().getId());
        }
    }

    public List<FoodRankingResponseDto> getFoodRanking(String email) {
        return foodDetailDao.getFoodRanking(email);
    }


}