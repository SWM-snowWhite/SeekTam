package food.backend.search.service;

//@SpringBootTest
//class searchRepositoryTest {
//
//    @Autowired
//    private FoodMainRepository foodMainRepository;
//
//    private Long foodId;
//
//    @BeforeEach
//    public void init() {
//        foodId = 1234L;
//    }
//
//    @Test
//    public void searchTestFromDB() {
//        FoodMain targetFoodMain = foodMainRepository.searchByFoodId(foodId);
//
//        Assertions.assertNotNull(targetFoodMain);
//        Assertions.assertTrue(targetFoodMain.getFoodId() == 1234L);
//        Assertions.assertEquals(targetFoodMain.getFoodType(), "가공식품");
//
//
//    }
//
//}