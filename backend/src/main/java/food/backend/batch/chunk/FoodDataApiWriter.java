package food.backend.batch.chunk;

import food.backend.batch.dto.FoodNutritionDto;
import food.backend.batch.service.StoreFoodDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class FoodDataApiWriter implements ItemWriter<List<FoodNutritionDto>> {

    private final StoreFoodDataService storeFoodDataService;

    //JdbcBatchItemWriter를 사용해서 List<FoodNutritionDto>를 한번에 저장하도록 변경해야함 -> 성능 개선
    @Override
    public void write(List<? extends List<FoodNutritionDto>> items) throws Exception {
        log.info(">>>>>>write() 호출");
        items.stream()
            .flatMap(List::stream)
            .forEach(FoodNutritionDto -> {
                FoodNutritionDto.setUpdateDate(LocalDateTime.now());
                storeFoodDataService.saveFoodDataToDatabase(FoodNutritionDto);
            });
    }

}

