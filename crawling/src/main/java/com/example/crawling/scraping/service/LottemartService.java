package com.example.crawling.scraping.service;

import com.example.crawling.scraping.dao.ScrapingDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;



@Service
@Slf4j
@RequiredArgsConstructor
public class LottemartService implements ShoppingMallService {
    private final String className = "s-goods-title";
    private final List<String> urls = Arrays.asList("https://www.lotteon.com/p/display/shop/seltDpShop/30308?callType=menu");
    private final ElasticSearchService elasticSearchService;
    private final DataProcessingService dataProcessingService;
    private final ScrapingDao scrapingDao;
    private final WebDriver webDriver;

    @Override
    public void crawling() throws SQLException, ClassNotFoundException {
        ArrayList<String> keywordList = new ArrayList<>();

        urls.forEach(url -> {
            webDriver.get(url);
            webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            List<WebElement> elements = webDriver.findElements(By.className(className));

            for (WebElement element : elements) {
                keywordList.add(element.getText());
            }
        });

        log.info("****************** 롯데마트 크롤링 ************************");
        log.info("데이터 총 개수: " + keywordList.size());

        // 형태소 분석 작업
        ArrayList<String> analyzedList = elasticSearchService.separateMorpheme(keywordList);
        List<Map.Entry<String, Integer>> top10List = dataProcessingService.remainTop10List(analyzedList);

        // DB 저장
//            scrapingDao.storeRdb(analyzedList);
//            scrapingDao.storeRedis(analyzedList);
    }
}