//package food.backend;
//
//
//
//import food.backend.search.model.FoodRepository;
//import org.junit.After;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class FoodRepositoryTest {
//    @Autowired
//    private FoodRepository foodRepository;
//
//    @After
//    public void cleanup() {
//        foodRepository.deleteAll();
//    }
//
////    @Test
////    public void testtt() {
////        // given
////        String title = "테스트 게시글";
////        String content = "테스트 본문";
////
////        foodsRepository.save(ViewsRanking.builder()
////                .foodKeyword("테스트 키워드")
////                .liked(false)
////                .calories(300)
////                .build());
////
////        // when
////        List<ViewsRanking> viewsRankingList = foodsRepository.findAll();
////
////        // then
////        ViewsRanking viewsRanking = viewsRankingList.get(0);
////        assertThat(viewsRanking.getRanking()).isEqualTo(1);
////        assertThat(viewsRanking.getFoodKeyword()).isEqualTo("테스트 키워드");
////        assertThat(viewsRanking.getCalories()).isEqualTo(300);
////    }
//}
