package com.example.crawling;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@EnableScheduling
@EnableBatchProcessing
@SpringBootApplication
@Slf4j
public class CrawlingApplication {
    enum ShoppingMall {
        COU, KUR, EMA, HOM, LOT
    }

    // Driver configuration
    public static String WEB_DRIVER_ID = "webdriver.gecko.driver";
    public static String WEB_DRIVER_PATH = "geckodriver";

    public static void main(String[] args) throws IOException {
        HashMap<ShoppingMall, HashMap<String, Object>> crawlingSiteInfo = new HashMap<>();

        // Define website crawling information
        crawlingSiteInfo.put(ShoppingMall.COU, getCoupangInfo());
        crawlingSiteInfo.put(ShoppingMall.KUR, getKurlyInfo());
        crawlingSiteInfo.put(ShoppingMall.EMA, getEmartInfo());
        crawlingSiteInfo.put(ShoppingMall.HOM, getHomeplusInfo());
        crawlingSiteInfo.put(ShoppingMall.LOT, getLotteInfo());

        ArrayList<String> keywordList = new ArrayList<>();

        try {
            for (Map.Entry<ShoppingMall, HashMap<String, Object>> entry : crawlingSiteInfo.entrySet()) {
                ArrayList<String> data = crawling(entry.getKey(), entry.getValue());
                log.info("******************************************");
                log.info("크롤링한 데이터 갯수: " + data.size());
                keywordList.addAll(data);
            }
        } catch (IOException e) {
            log.error("크롤링 중 오류 발생: " + e.getMessage());
        }

        log.info("******************************************");
        log.info("데이터 총 개수: " + keywordList.size());
    }

    private static HashMap<String, Object> getCoupangInfo() {
        HashMap<String, Object> coupangInfo = new HashMap<>();
        coupangInfo.put("url", Arrays.asList(
                "https://www.coupang.com/np/categories/393760?listSize=120&brand=&offerCondition=&filterType=&isPriceRange=false&minPrice=&maxPrice=&page=1&channel=user&fromComponent=N&selectedPlpKeepFilter=&sorter=bestAsc&filter=&rating=0",
                "https://www.coupang.com/np/categories/393760?listSize=120&brand=&offerCondition=&filterType=&isPriceRange=false&minPrice=&maxPrice=&page=2&channel=user&fromComponent=N&selectedPlpKeepFilter=&sorter=bestAsc&filter=&rating=0"
        ));
        coupangInfo.put("className", "name");
        return coupangInfo;
    }

    private static HashMap<String, Object> getKurlyInfo() {
        HashMap<String, Object> kurlyInfo = new HashMap<>();
        kurlyInfo.put("url", Arrays.asList(
                "https://www.kurly.com/collections/market-best?page=1&per_page=96&sorted_type=4",
                "https://www.kurly.com/collections/market-best?page=2&per_page=96&sorted_type=4",
                "https://www.kurly.com/collections/market-best?page=3&per_page=96&sorted_type=4"
        ));
        kurlyInfo.put("className", "css-1dry2r1");
        return kurlyInfo;
    }

    private static HashMap<String, Object> getEmartInfo() {
        HashMap<String, Object> emartInfo = new HashMap<>();
        emartInfo.put("url", Arrays.asList("https://emart.ssg.com/best/main.ssg"));
        emartInfo.put("className", "mnemitem_goods_tit");
        return emartInfo;
    }

    private static HashMap<String, Object> getHomeplusInfo() {
        HashMap<String, Object> homeplusInfo = new HashMap<>();
        homeplusInfo.put("url", Arrays.asList("https://front.homeplus.co.kr/best?gnbNo=3"));
        homeplusInfo.put("className", "css-12cdo53-defaultStyle-Typography-ellips");
        return homeplusInfo;
    }

    private static HashMap<String, Object> getLotteInfo() {
        HashMap<String, Object> lotteInfo = new HashMap<>();
        lotteInfo.put("url", Arrays.asList("https://www.lotteon.com/p/display/shop/seltDpShop/30308?callType=menu"));
        lotteInfo.put("className", "s-goods-title");
        return lotteInfo;
    }

    private static ArrayList<String> crawling(ShoppingMall mallName, HashMap<String, Object> siteInfo) throws IOException {
        log.info("siteInfo ::::: " + siteInfo);
        List<String> urlList = (List<String>) siteInfo.get("url");
        String className = (String) siteInfo.get("className");

        log.info("mallName ::::: " + mallName);
        log.info("urlList ::::: " + urlList);
        log.info("className ::::: " + className);

        // Set WebDriver properties
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // WebDriver options
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-default-apps");

        WebDriver driver = new FirefoxDriver(options);

        log.info("--------------------------------------------------------------------");

        ArrayList<String> keywordList = new ArrayList<>();
        urlList.forEach(url -> {
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            List<WebElement> elements = driver.findElements(By.className(className));
            for (WebElement element : elements) {
                log.info("content.data() ::::" + element.getText());
                keywordList.add(element.getText());
            }
            log.info("--------------------------------------------------------------------");
            log.info("elements" + elements.size() + "");
        });
        return keywordList;
    }
}
