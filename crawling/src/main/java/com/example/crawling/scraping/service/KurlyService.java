package com.example.crawling.scraping.service;

import com.example.crawling.scraping.dao.ScrapingDao;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class KurlyService implements ShoppingMallService {
    private HashMap<String, Object> MallInfo;
    private String className = "css-1dry2r1";
    private List<String> urls = Arrays.asList(
        "https://www.kurly.com/collections/market-best?page=1&per_page=96&sorted_type=4",
        "https://www.kurly.com/collections/market-best?page=2&per_page=96&sorted_type=4",
        "https://www.kurly.com/collections/market-best?page=3&per_page=96&sorted_type=4"
    );;
    private ObjectProvider<WebDriver> webDriverObjectProvider;
    private ElasticSearchService elasticSearchService;
    private ScrapingDao scrapingDao;

    @Override
    public void crawling() throws SQLException, ClassNotFoundException {
        ArrayList<String> keywordList = new ArrayList<>();
        WebDriver webDriver = webDriverObjectProvider.getObject();

        try {
            urls.forEach(url -> {
                webDriver.get(url);
                webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                List<WebElement> elements = webDriver.findElements(By.className(className));
                for (WebElement element : elements) {
                    keywordList.add(element.getText());
                }
            });

            log.info("****************** 컬리 크롤링 ************************");
            log.info("데이터 총 개수: " + keywordList.size());

            // 형태소 분석 작업
            List<Map.Entry<String, Integer>> analyzedList = elasticSearchService.separateMorpheme(keywordList);

            // DB 저장
            scrapingDao.storeRdb(analyzedList);
            scrapingDao.storeRedis(analyzedList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            webDriver.close();
        }
    }
}
