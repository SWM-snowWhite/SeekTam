package food.backend.batch.chunk;

import food.backend.batch.dto.FoodNutritionDto;
import food.backend.batch.service.FoodDataApiService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;

/**
 * ID : ST-C-100-J 작성자 : 임동훈(snowcrab382@naver.com) 버전 : 1.0.0 작성일 : 2023-10-20
 */
@Slf4j
public class FoodDataApiReader implements ItemReader<List<FoodNutritionDto>> {

    private final FoodDataApiService foodDataApiService;
    private final int pageSize;
    private int currentPage = 0;

    /**
     * 생성자 getPageSize() : FoodDataApiService의 getPageSize() 메서드를 호출하여 pageSize를 초기화
     *
     * @param foodDataApiService
     */
    public FoodDataApiReader(FoodDataApiService foodDataApiService) {
        this.foodDataApiService = foodDataApiService;
        this.pageSize = foodDataApiService.getPageSize();
    }

    /**
     * FoodDataApiService의 getFoodDataResponse() 메서드를 호출하여 응답을 받아온다. 메서드 실행 시 currentPage를 1 증가시키고, pageSize보다 크면 null을
     * 반환하여 reader를 종료한다.
     *
     * @return 응답받은 데이터를 FoodNutritionDto로 변환하여 반환
     */
    @Override
    public List<FoodNutritionDto> read() {
        currentPage++;
        log.info(">>>>>>read() 호출, Current Page: {}", currentPage);

        if (currentPage > pageSize) {
            return null;
        }

        String response = foodDataApiService.getFoodDataResponse(currentPage);

        return foodDataApiService.bindFoodDataToDto(response);
    }
}