package food.backend.batch.chunk;

import food.backend.batch.dto.FoodNutritionDto;
import food.backend.batch.service.FoodDataApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;

import java.util.List;


@Slf4j
public class FoodDataApiReader implements ItemReader<List<FoodNutritionDto>> {

    private final FoodDataApiService foodDataApiService;
    private final int pageSize;
    private int currentPage = 0;

    public FoodDataApiReader(FoodDataApiService foodDataApiService) {
        this.foodDataApiService = foodDataApiService;
        this.pageSize = foodDataApiService.getPageSize();
    }

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