package food.backend.search.service;



import food.backend.member.Member;
import food.backend.member.MemberRepository;
import food.backend.search.dao.FoodDetailDao;
import food.backend.search.dao.ProductDao;
import food.backend.search.dao.ProductDetailDao;
import food.backend.search.dao.ProductKeywordDao;
import food.backend.search.dto.FoodRankingResponseDto;
import food.backend.search.dto.ProductDetailDto;
import food.backend.search.dto.ProductDto;
import food.backend.search.model.KeywordAndNutrientEs;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductSearchService {

    private final ProductKeywordDao productKeywordDao;
    private final ProductDao productDao;
    private final ProductDetailDao productDetailDao;
    private final FoodDetailDao foodDetailDao;
    private final MemberRepository memberRepository;
    public List<String> getProductByKeyword(String keyword) {
        return productKeywordDao.getProductByKeyword(keyword);
    }

    public List<ProductDto> getProductByKeywordAndNutrient(KeywordAndNutrientEs params) {
        return productDao.getProductByKeywordAndNutrient(params);
    }

    public ProductDetailDto getProductDetail(Long foodId, String email) {
        increaseViewCount(foodId, email);
        return productDetailDao.getProductDetail(foodId);
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
