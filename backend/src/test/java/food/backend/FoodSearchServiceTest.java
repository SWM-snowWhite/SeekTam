package food.backend.search.service;

import food.backend.member.Member;
import food.backend.search.dao.FoodDetailDao;
import food.backend.search.dao.FoodKeywordDao;
import food.backend.search.dao.FoodListDao;
import food.backend.member.MemberRepository;
import food.backend.search.dto.FoodDetailDto;
import food.backend.search.dto.FoodListDto;
import food.backend.search.dto.FoodRankingResponseDto;
import food.backend.search.model.KeywordAndNutrient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodSearchServiceTest {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private FoodKeywordDao foodKeywordDAO;

    @Mock
    private FoodListDao foodListDAO;

    @Mock
    private FoodDetailDao foodDetailDao;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private FoodSearchService foodSearchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void 식품_키워드_조회() {
        // Given
        String keyword = "치킨";
        List<String> expectedFoods = Arrays.asList("후라이드 치킨", "치킨 버거", "치킨 피자");
        when(foodKeywordDAO.getFoodByNameContaining(keyword)).thenReturn(expectedFoods);

        // When
        List<String> result = foodSearchService.getFoodByNameContaining(keyword);

        // Then
        assertEquals(expectedFoods, result);
        verify(foodKeywordDAO, times(1)).getFoodByNameContaining(keyword);
    }

    @Test
    void 조건에_맞는_식품_키워드_조회() {
        // Given
        KeywordAndNutrient params =
                KeywordAndNutrient.builder()
                        .keyword("치킨")
                        .chocdf_con("1")
                        .fatce_con("30")
                        .prot_con("10")
                        .enerc_con("100")
                        .build();

        List<FoodListDto> expectedFoodList = Arrays.asList(
                FoodListDto.builder().foodId(1L).foodName("치킨 카레").companyName("교촌").build(),
                FoodListDto.builder().foodId(1L).foodName("치킨 샐러드").companyName("BBQ").build(),
                FoodListDto.builder().foodId(1L).foodName("치킨 버킷").companyName("KFC").build()
        );
        when(foodListDAO.getFoodListByNameContaining(params)).thenReturn(expectedFoodList);

        // When
        List<FoodListDto> result = foodSearchService.getFoodListByNameContaining(params);

        // Then
        assertEquals(expectedFoodList, result);
        verify(foodListDAO, times(1)).getFoodListByNameContaining(params);
    }

    @Test
    public void 식품_아이디로_식품_상세정보_조회() {
        // Given
        Long foodId = 1L;
        String email = "test@email.com";
        FoodDetailDto expectedFoodDetail = FoodDetailDto.builder().foodCd("A0001").foodNm("치킨").build();
        expectedFoodDetail.setFoodId(foodId); // Add more setters if required

        Member member = Member.builder().id(1L).build();

        when(memberRepository.findByEmail(anyString())).thenReturn(Optional.of(member));
        when(foodDetailDao.getFoodDataById(anyLong())).thenReturn(expectedFoodDetail);

        // When
        FoodDetailDto actualFoodDetail = foodSearchService.getFoodDetailById(foodId, email);

        // Then
        assertEquals(expectedFoodDetail, actualFoodDetail);
    }

    @Test
    public void 식품_랭킹_조회() {
        // Given
        String email = "test@example.com";
        FoodRankingResponseDto mockedResponse = FoodRankingResponseDto.builder()
                .foodId(1L)
                .foodName("Test Food")
                .calories(100)
                .ranking(1)
                .build();

        when(foodDetailDao.getFoodRanking(anyString())).thenReturn(Collections.singletonList(mockedResponse));

        // When
        List<FoodRankingResponseDto> result = foodSearchService.getFoodRanking(email);

        // Then
        assertEquals(1, result.size());
        assertEquals(mockedResponse, result.get(0));
    }
}
