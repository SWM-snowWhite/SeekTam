package food.backend.batch.job;

import food.backend.batch.chunk.FoodDataApiReader;
import food.backend.batch.chunk.FoodDataApiWriter;
import food.backend.batch.dto.FoodNutritionDto;
import food.backend.batch.service.FoodDataApiService;
import food.backend.batch.service.StoreFoodDataService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ID : ST-C-100-J 작성자 : 임동훈(snowcrab382@naver.com) 버전 : 1.0.0 작성일 : 2023-10-20
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class FetchFoodDataJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final FoodDataApiService foodDataApiService;
    private final StoreFoodDataService storeFoodDataService;

    /**
     * JobBuilderFactory를 통해 Job을 생성하고, Job의 이름은 updateFoodDataJob으로 지정한다. Job은 setTotalDataSizeStep과
     * storeFoodDataStep으로 구성되어 있다.
     *
     * @return Job
     */
    public Job updateFoodDataJob() {
        log.info(">>>>>>Job start");
        return jobBuilderFactory.get("updateFoodDataJob")
                .start(setTotalDataSizeStep())
                .next(storeFoodDataStep())
                .build();
    }

    /**
     * StepBuilderFactory를 통해 Step을 생성하고, Step의 이름은 checkTotalDataSizeStep으로 지정한다. Step은 tasklet을 통해 foodDataApiService의
     * setTotalData()를 실행한다. 해당 작업을 통해 공공데이터 전국통식품영양성정보표준데이터 DB 전체 데이터의 개수를 구한다. 공공데이터 API URL :
     * https://www.data.go.kr/data/15100064/standard.do
     *
     * @return Step
     */
    @Bean
    @JobScope
    public Step setTotalDataSizeStep() {
        log.info(">>>>>>Step1 start");
        return stepBuilderFactory.get("checkTotalDataSizeStep")
                .tasklet((contribution, chunkContext) -> {
                    foodDataApiService.setTotalData();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    /**
     * StepBuilderFactory를 통해 Step을 생성하고, Step의 이름은 storeFoodDataStep으로 지정한다. Step은 chunk를 통해 foodDataApiReader와
     * foodDataApiWriter를 실행한다.
     *
     * @return Step
     */
    @Bean
    @JobScope
    public Step storeFoodDataStep() {
        log.info(">>>>>>Step2 start");
        return stepBuilderFactory.get("storeFoodDataStep")
                .<List<FoodNutritionDto>, List<FoodNutritionDto>>chunk(1)
                .reader(foodDataApiReader())
                .writer(foodDataApiWriter())
                .build();
    }

    /**
     * StepBuilderFactory를 통해 Step을 생성하고, Step의 이름은 storeFoodDataStep으로 지정한다. 공공데이터 API 요청을 통해 데이터를 전달받아 가공한다.
     *
     * @return Step
     */
    @Bean
    @StepScope
    public FoodDataApiReader foodDataApiReader() {
        log.info(">>>>>>Reader start");
        return new FoodDataApiReader(foodDataApiService);
    }

    /**
     * StepBuilderFactory를 통해 Step을 생성하고, Step의 이름은 storeFoodDataStep으로 지정한다. 가공된 데이터를 전달받아 MySQL DB에 저장한다.
     *
     * @return Step
     */
    @Bean
    @StepScope
    public FoodDataApiWriter foodDataApiWriter() {
        log.info(">>>>>>Writer start");
        return new FoodDataApiWriter(storeFoodDataService);
    }

}