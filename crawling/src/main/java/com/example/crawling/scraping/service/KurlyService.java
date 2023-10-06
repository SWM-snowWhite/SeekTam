package com.example.crawling.scraping.service;

import com.example.crawling.scraping.dao.ScrapingDao;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class KurlyService implements ShoppingMallService {
    private final String className = "css-1dry2r1";
    private final List<String> urls = Arrays.asList(
        "https://www.kurly.com/collections/market-best?page=1&per_page=96&sorted_type=4",
        "https://www.kurly.com/collections/market-best?page=2&per_page=96&sorted_type=4",
        "https://www.kurly.com/collections/market-best?page=3&per_page=96&sorted_type=4"
    );;
    private final ElasticSearchService elasticSearchService;
    private final ScrapingDao scrapingDao;
    private final WebDriver webDriver;
    private final DataProcessingService dataProcessingService;
    @Override
    public void crawling() throws SQLException, ClassNotFoundException {
        ArrayList<String> keywordList = new ArrayList<>();

        try {
            for (String url : urls) {
                webDriver.get(url);
                webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                List<WebElement> elements = webDriver.findElements(By.className(className));
                for (WebElement element : elements) {
                    keywordList.add(element.getText());
                }
            };

            log.info("****************** 컬리 크롤링 ************************");
            log.info("데이터 총 개수: " + keywordList.size());

            // 형태소 분석 작업
            ArrayList<String> analyzedKeywordList = elasticSearchService.separateMorpheme(keywordList);

            log.info("****************** 컬리 분석 리스트************************");
            log.info(analyzedKeywordList.toString());
            log.info("******************************************************");

            // 필터 작업
            List<String> filteredList = dataProcessingService.filterUselessKeyword(analyzedKeywordList);

            log.info("****************** 필터된 리스트************************");
            log.info(filteredList.toString());
            log.info("******************************************************");

            List<Map.Entry<String, Integer>> top10List = dataProcessingService.remainTop10List(filteredList);

            log.info("****************** Top10 리스트************************");
            log.info(filteredList.toString());
            log.info("******************************************************");

            // DB 저장
            scrapingDao.storeRdb(top10List);
            scrapingDao.storeRedis(top10List);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
