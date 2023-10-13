package com.example.crawling.scraping.service;

import com.example.crawling.scraping.dao.ScrapingDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScrapingScheduler {
    private final Scraping ScrapingService;
    private final ScrapingDao scrapingDao;
    private final ElasticSearch elasticSearchService;
    private final DataProcessingService dataProcessingService;
    // 매 10분마다 시작
    @Scheduled(fixedDelay = 1000 * 60 * 10)
    public void scarpingJobScheduler() {
        List<String> keywordList = new ArrayList<>();
        log.info("스케쥴러 시작" + new Date().toString());

        try {
            // 쿠팡 사이트 에러
//            List<String> coupangUrls = Arrays.asList(
//                    "https://www.coupang.com/np/categories/393760?listSize=120&brand=&offerCondition=&filterType=&isPriceRange=false&minPrice=&maxPrice=&page=1&channel=user&fromComponent=N&selectedPlpKeepFilter=&sorter=bestAsc&filter=&rating=0",
//                    "https://www.coupang.com/np/categories/393760?listSize=120&brand=&offerCondition=&filterType=&isPriceRange=false&minPrice=&maxPrice=&page=2&channel=user&fromComponent=N&selectedPlpKeepFilter=&sorter=bestAsc&filter=&rating=0"
//            );
//            String coupangClassName = "name";
//            coupangService.crawling();

            // 정상 작동
            String kurlyClassName = "css-1dry2r1";
            List<String> kurlyUrls = Arrays.asList(
                    "https://www.kurly.com/collections/market-best?page=1&per_page=96&sorted_type=4",
                    "https://www.kurly.com/collections/market-best?page=2&per_page=96&sorted_type=4",
                    "https://www.kurly.com/collections/market-best?page=3&per_page=96&sorted_type=4"
            );;
            List<String> kurlyScrapedList = ScrapingService.crawling(kurlyClassName, kurlyUrls);
            List<String> kurlyAnalyzedList = elasticSearchService.separateMorpheme(kurlyScrapedList);
            keywordList.addAll(kurlyAnalyzedList);


            String emartClassName = "mnemitem_goods_tit";
            List<String> emartUrls = Arrays.asList("https://emart.ssg.com/best/main.ssg");
            List<String> emartScrapedList = ScrapingService.crawling(emartClassName, emartUrls);
            List<String> emartAnalyzedList = elasticSearchService.separateMorpheme(emartScrapedList);
            keywordList.addAll(emartAnalyzedList);

            String lottemartClassName = "s-goods-title";
            List<String> lottemartUrls = Arrays.asList("https://www.lotteon.com/p/display/shop/seltDpShop/30308?callType=menu");
            List<String> lottemartScrapedList = ScrapingService.crawling(lottemartClassName, lottemartUrls);
            List<String> lottemartAnalyzedList = elasticSearchService.separateMorpheme(lottemartScrapedList);
            keywordList.addAll(lottemartAnalyzedList);

            String homeplusClassName = "css-12cdo53-defaultStyle-Typography-ellips";
            List<String> homeplusUrls = Arrays.asList("https://front.homeplus.co.kr/best?gnbNo=3");
            List<String> homeplusScrapedList = ScrapingService.crawling(homeplusClassName, homeplusUrls);
            List<String> homeplusAnalyzedList = elasticSearchService.separateMorpheme(homeplusScrapedList);
            keywordList.addAll(homeplusAnalyzedList);

            // 불필요한 키워드 제거
            List<String> filteredkeywordList = dataProcessingService.filterUselessKeyword(keywordList);

            // 키워드 랭킹 매기기
            HashMap<String, Integer> keywordRankMap = rankKeywordListMap(filteredkeywordList);

            // 상위 10개 키워드 추출
            List<Map.Entry<String, Integer>> top10List = remainTop10List(keywordRankMap);

            // Redis 및 RDS에 저장
            log.info("****************** DB 저장 시작 ************************");
            scrapingDao.storeRdb(top10List);
//            scrapingDao.storeRedis(top10List);
            log.info("****************** DB 저장 완료 ************************");
        } catch (Exception e) {
            log.error("스케줄러 에러 발생", e);
        }
    }
    public static HashMap<String, Integer> rankKeywordListMap (List<String> keywordList) {
        HashMap<String, Integer> keywordRankMap = new HashMap<>();
        keywordList.forEach(word -> keywordRankMap.put(word, keywordRankMap.getOrDefault(word, 0) + 1));
        return keywordRankMap;
    }

    public static List<Map.Entry<String, Integer>> remainTop10List(HashMap<String, Integer> keywordRankMap) {

        List<Map.Entry<String, Integer>> sortedList =
                keywordRankMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .collect(Collectors.toList());

        ArrayList<Map.Entry<String, Integer>> sortedArrayList = new ArrayList<>(sortedList);
        List<Map.Entry<String, Integer>> top10List = sortedArrayList.subList(0, Math.min(10, sortedArrayList.size()));
        return top10List;
    }
}
