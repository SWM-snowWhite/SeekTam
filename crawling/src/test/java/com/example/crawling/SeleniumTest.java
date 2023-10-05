package com.example.crawling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.sql.Time;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTest {

    private static FirefoxDriver driver;
    private static WebDriverWait wait;
    private WebElement element;
    private static String emartClassName = "mnemitem_goods_tit";
    private static List<String> emartUrls = Arrays.asList("https://emart.ssg.com/best/main.ssg");
    private static String kurlyClassName = "css-1dry2r1";
    private static List<String> kurlyUrls = Arrays.asList(
            "https://www.kurly.com/collections/market-best?page=1&per_page=96&sorted_type=4",
            "https://www.kurly.com/collections/market-best?page=2&per_page=96&sorted_type=4",
            "https://www.kurly.com/collections/market-best?page=3&per_page=96&sorted_type=4"
    );
    ;
    private static String coupangClassName = "name";
    private static List<String> coupangUrls = Arrays.asList(
            "https://www.coupang.com/np/categories/393760?listSize=120&brand=&offerCondition=&filterType=&isPriceRange=false&minPrice=&maxPrice=&page=1&channel=user&fromComponent=N&selectedPlpKeepFilter=&sorter=bestAsc&filter=&rating=0",
            "https://www.coupang.com/np/categories/393760?listSize=120&brand=&offerCondition=&filterType=&isPriceRange=false&minPrice=&maxPrice=&page=2&channel=user&fromComponent=N&selectedPlpKeepFilter=&sorter=bestAsc&filter=&rating=0"
    );
    private static List<String> lottemartUrls = Arrays.asList("https://www.lotteon.com/p/display/shop/seltDpShop/30308?callType=menu");
    private static String lottemartClassName = "s-goods-title";
    private static String homeplusClassName = "css-12cdo53-defaultStyle-Typography-ellips";
    private static List<String> homeplusUrls = Arrays.asList("https://front.homeplus.co.kr/best?gnbNo=3");

    public static int SHORT_WAIT = 5;
    public static int MEDIUM_WAIT = 10;
    public static int LONG_WAIT = 15;
    static {
        System.setProperty("webdriver.gecko.driver", "geckodriver");
    }

    @BeforeEach
    public void driverSetup() {
        FirefoxOptions options = new FirefoxOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("remote-debugging-port=9222");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-default-apps");
        driver = new FirefoxDriver(options);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }


    /**
     * Firefox Driver Test
     */
    @Test
    public void checkDriver() {
        Assertions.assertNotNull(driver);
    }

    /**
     * emartMall 접속 테스트
     */
    @Test
    public void enterEmartmall() {
        // give
        int length = emartUrls.size();
        for (int i = 0; i < length; i++) {
            String url = emartUrls.get(i);
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // when
            WebElement element = driver.findElement(By.className(emartClassName));

            // then
            assertNotNull(element);
        }
    }

    /**
     * kurly 접속 테스트
     */
    @Test
    public void enterKurly() {
        // give
        int length = kurlyUrls.size();
        for (int i = 0; i < length; i++) {
            String url = kurlyUrls.get(i);
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // when
            WebElement element = driver.findElement(By.className(kurlyClassName));

            // then
            assertNotNull(element);
        }
    }

    /**
     * coupang 접속 테스트
     */
    @Test
    public void enterCoupang() {
        // give
        int length = coupangUrls.size();
        for (int i = 0; i < length; i++) {
            String url = coupangUrls.get(i);
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // when
            WebElement element = driver.findElement(By.className(coupangClassName));

            // then
            assertNotNull(element);
        }
    }

    /**
     * homeplus 접속 테스트
     */
    @Test
    public void enterHomeplus() {
        // give
        int length = homeplusUrls.size();
        for (int i = 0; i < length; i++) {
            String url = homeplusUrls.get(i);
            System.out.println("=====================================");
            System.out.println("url        ::::::: " + url);
            System.out.println("=====================================");
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // when
            WebElement element = driver.findElement(By.className(homeplusClassName));

            // then
            assertNotNull(element);
        }
    }

    /**
     * lottemart 접속 테스트
     */
//    @Test
//    public void enterLottemart() {
//        // give
//            int length = lottemartUrls.size();
//            for (int i = 0; i < length; i++) {
//                String url = lottemartUrls.get(i);
//                System.out.println("=====================================");
//                System.out.println("url        ::::::: " + url);
//                System.out.println("=====================================");
//                driver.get(url);
////                wait = new WebDriverWait(driver, Duration.ofSeconds(20));
////                wait.until((ExpectedCondition<Boolean>) wd ->
////                        ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("interactive"));
//
//            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//
//                // when
//                WebElement element = driver.findElement(By.className(lottemartClassName));
//
//                // then
//                assertNotNull(element);
//        }
//    }


}
