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
    private HashMap<String, Object> MallInfo;
    private final String className = "mnemitem_goods_tit";
    private final List<String> urls = Arrays.asList("https://emart.ssg.com/best/main.ssg");
    private final ElasticSearchService elasticSearchService;
    private final ScrapingDao scrapingDao;
    private final WebDriver webDriver;

    @Override
    public void crawling() throws SQLException, ClassNotFoundException {
        ArrayList<String> keywordList = new ArrayList<>();

        try {
            for (String url : urls) {
                webDriver.get(url);
//                waitForLoad(webDriver);
                webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                List<WebElement> elements = webDriver.findElements(By.className(className));
                for (WebElement element : elements) {
                    keywordList.add(element.getText());
                }
            }

            log.info("****************** 이마트 크롤링 ************************");
            log.info("데이터 총 개수: " + keywordList.size());

            // 형태소 분석 작업
            List<Map.Entry<String, Integer>> analyzedList = elasticSearchService.separateMorpheme(keywordList);
            scrapingDao.storeRdb(analyzedList);
            scrapingDao.storeRedis(analyzedList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void waitForLoad(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("interactive"));
    }
}
