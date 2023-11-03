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

@Service
@RequiredArgsConstructor
public class ProductSearchService {

    private final ProductKeywordDao productKeywordDao;
    private final ProductDao productDao;
    private final ProductDetailDao productDetailDao;

    public List<String> getProductByKeyword(String keyword) {
        return productKeywordDao.getProductByKeyword(keyword);
    }

    public List<ProductDto> getProductByKeywordAndNutrient(KeywordAndNutrientEs params) {
        return productDao.getProductByKeywordAndNutrient(params);
    }

    public ProductDetailDto getProductDetail(Integer foodId) {
        return productDetailDao.getProductDetail(foodId);
    }
}
