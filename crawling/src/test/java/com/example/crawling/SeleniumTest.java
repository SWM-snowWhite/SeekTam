package com.example.crawling;

import org.springframework.boot.test.context.SpringBootTest;

package com.example.crawling;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SeleniumTest {

    private static FirefoxDriver driver;
    private static WebDriverWait wait;
    private WebElement element;
    private String emartClassName = "mnemitem_goods_tit";
    private List<String> emartUrls = Arrays.asList("https://emart.ssg.com/best/main.ssg");
    private String kurlyClassName = "css-1dry2r1";
    private List<String> kurlyUrls = Arrays.asList(
            "https://www.kurly.com/collections/market-best?page=1&per_page=96&sorted_type=4",
            "https://www.kurly.com/collections/market-best?page=2&per_page=96&sorted_type=4",
            "https://www.kurly.com/collections/market-best?page=3&per_page=96&sorted_type=4"
    );;
    private String coupangClassName = "name";
    private List<String> coupangUrls = Arrays.asList(
            "https://www.coupang.com/np/categories/393760?listSize=120&brand=&offerCondition=&filterType=&isPriceRange=false&minPrice=&maxPrice=&page=1&channel=user&fromComponent=N&selectedPlpKeepFilter=&sorter=bestAsc&filter=&rating=0",
            "https://www.coupang.com/np/categories/393760?listSize=120&brand=&offerCondition=&filterType=&isPriceRange=false&minPrice=&maxPrice=&page=2&channel=user&fromComponent=N&selectedPlpKeepFilter=&sorter=bestAsc&filter=&rating=0"
    );
    private List<String> lottemartUrls = Arrays.asList("https://www.lotteon.com/p/display/shop/seltDpShop/30308?callType=menu");
    private String lottemartClassName = "s-goods-title";
    private String homeplusClassName = "css-12cdo53-defaultStyle-Typography-ellips";
    private List<String> homeplusUrls = Arrays.asList("https://front.homeplus.co.kr/best?gnbNo=3");

    public static final int SHORT_WAIT = 5;
    public static final int MEDIUM_WAIT = 10;
    public static final int LONG_WAIT = 15;

    @BeforeClass
    public static void openBrowser() {
        System.setProperty("webdriver.gecko.driver", "geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofMillis(LONG_WAIT));
    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }



}
