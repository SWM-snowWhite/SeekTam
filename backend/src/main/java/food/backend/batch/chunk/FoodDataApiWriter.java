package food.backend.batch.chunk;

import food.backend.batch.dto.FoodNutritionDto;
import food.backend.batch.service.StoreFoodDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ID : ST-C-100-J
 * 작성자 : 임동훈(snowcrab382@naver.com)
 * 버전 : 1.0.0
 * 작성일 : 2023-10-20
 */
@Slf4j
@RequiredArgsConstructor
public class FoodDataApiWriter implements ItemWriter<List<FoodNutritionDto>> {

    private final StoreFoodDataService storeFoodDataService;

    /**
     * FoodDataApiService의 getFoodDataResponse() 메서드를 호출하여 응답을 받아온다.
     * List에 담긴 FoodNutritionDto를 하나씩 꺼내서 saveFoodDataToDatabase() 메서드를 호출하여 데이터베이스에 저장한다.
     * @param items FoodNutritionDto를 담은 List
     * @throws Exception 예외처리
     */
    //JdbcBatchItemWriter를 사용해서 List<FoodNutritionDto>를 한번에 저장하도록 변경해야함 -> 성능 개선
    @Override
    public void write(List<? extends List<FoodNutritionDto>> items) throws Exception {
        log.info(">>>>>>write() 호출");
        items.stream()
                .flatMap(List::stream)
                .forEach(storeFoodDataService::saveFoodDataToDatabase);
    }

}

