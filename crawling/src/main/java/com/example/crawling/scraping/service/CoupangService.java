package com.example.crawling.scraping.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import com.example.crawling.scraping.dao.ScrapingDao;

import java.sql.SQLException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoupangService implements ShoppingMallService {
    private final WebDriver webDriver;
    private final ElasticSearchService elasticSearchService;
    private final ScrapingDao scrapingDao;

    @Override
    public void crawling() throws SQLException, ClassNotFoundException {
        String className = "name";
        List<String> urls = Arrays.asList(
                "https://www.coupang.com/np/categories/393760?listSize=120&brand=&offerCondition=&filterType=&isPriceRange=false&minPrice=&maxPrice=&page=1&channel=user&fromComponent=N&selectedPlpKeepFilter=&sorter=bestAsc&filter=&rating=0",
                "https://www.coupang.com/np/categories/393760?listSize=120&brand=&offerCondition=&filterType=&isPriceRange=false&minPrice=&maxPrice=&page=2&channel=user&fromComponent=N&selectedPlpKeepFilter=&sorter=bestAsc&filter=&rating=0"
        );
        ArrayList<String> keywordList = new ArrayList<>();

        try {
            for (String url : urls) {
                webDriver.get(url);
                // 최대 10초 대기
//                waitForLoad(webDriver);
//                WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofMillis(100000));
//                List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(className)));
                webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//                driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//                List<WebElement> elements = wait.until(webDriver -> webDriver.findElements(By.className(className)));

                List<WebElement> elements = webDriver.findElements(By.className("name"));
                for (WebElement element : elements) {
                    keywordList.add(element.getText());
                }
            }

            log.info("****************** 쿠팡 크롤링 ************************");
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
        new WebDriverWait(driver, Duration.ofSeconds(40)).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("interactive"));
    }
}
