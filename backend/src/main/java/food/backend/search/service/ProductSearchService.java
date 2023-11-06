package food.backend.search.service;


import food.backend.search.dao.ProductDao;
import food.backend.search.dao.ProductDetailDao;
import food.backend.search.dao.ProductKeywordDao;
import food.backend.search.dto.ProductDetailDto;
import food.backend.search.dto.ProductDto;
import food.backend.search.model.KeywordAndNutrientEs;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ID : ST-C-120-J 작성자 : 임동훈(snowcrab382@naver.com) 버전 : 1.0.0 작성일 : 2023-11-06
 */
@Service
@RequiredArgsConstructor
public class ProductSearchService {

    private final ProductKeywordDao productKeywordDao;
    private final ProductDao productDao;
    private final ProductDetailDao productDetailDao;

    /**
     * 키워드가 포함된 상품명 요청
     *
     * @param keyword : 검색 키워드
     * @return List
     */
    public List<String> getProductByKeyword(String keyword) {
        return productKeywordDao.getProductByKeyword(keyword);
    }

    /**
     * 키워드가 포함되고 영양소 조건에 부합하는 상품ID,상품명,제조사명 요청
     *
     * @param params : 검색 키워드, 칼로리, 칼로리 조건(0 or 1), 탄수화물, 탄수화물 조건, 단백질, 단백질 조건, 지방, 지방 조건
     * @return List
     * @see ProductDto
     * @see KeywordAndNutrientEs
     */
    public List<ProductDto> getProductByKeywordAndNutrient(KeywordAndNutrientEs params) {
        return productDao.getProductByKeywordAndNutrient(params);
    }

    /**
     * 상품ID에 해당하는 상품 상세 정보 요청
     *
     * @param foodId : 상품 ID
     * @return ProductDetailDto
     */
    public ProductDetailDto getProductDetail(Integer foodId) {
        return productDetailDao.getProductDetail(foodId);
    }
}
