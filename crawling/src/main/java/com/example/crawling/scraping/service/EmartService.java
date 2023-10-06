package com.example.crawling.scraping.service;

import com.example.crawling.scraping.dao.ScrapingDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmartService implements ShoppingMallService {
    private final String className = "mnemitem_goods_tit";
    private final List<String> urls = Arrays.asList("https://emart.ssg.com/best/main.ssg");
    private final ElasticSearchService elasticSearchService;
    private final ScrapingDao scrapingDao;
    private final WebDriver webDriver;
    private final DataProcessingService dataProcessingService;

    @Override
    public void crawling() throws SQLException, ClassNotFoundException {
        ArrayList<String> keywordList = new ArrayList<>();

        for (String url : urls) {
            webDriver.get(url);
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            List<WebElement> elements = webDriver.findElements(By.className(className));
            for (WebElement element : elements) {
                keywordList.add(element.getText());
            }
        }

        log.info("****************** 이마트 크롤링 ************************");
        log.info("데이터 총 개수: " + keywordList.size());

        // 형태소 분석 작업
        ArrayList<String> analyzedKeywordList = elasticSearchService.separateMorpheme(keywordList);

        // 필터 작업
        List<String> filteredList = dataProcessingService.filterUselessKeyword(analyzedKeywordList);
        List<Map.Entry<String, Integer>> top10List = dataProcessingService.remainTop10List(filteredList);


        // DB 저장
//            scrapingDao.storeRdb(analyzedKeywordList);
//            scrapingDao.storeRedis(analyzedKeywordList);
    }
}
