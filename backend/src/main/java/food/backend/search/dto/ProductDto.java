package food.backend.search.dto;

import lombok.Builder;
import lombok.Data;

/**
 * ID : ST-C-120-J 작성자 : 임동훈(snowcrab382@naver.com) 버전 : 1.0.0 작성일 : 2023-11-06
 */
@Builder
@Data
public class ProductDto {

    private Integer foodId;
    private String foodName;
    private String companyName;
}
