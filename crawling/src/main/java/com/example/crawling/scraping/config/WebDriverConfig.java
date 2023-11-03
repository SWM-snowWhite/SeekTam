package com.example.crawling.scraping.config;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Slf4j
@Configuration
public class WebDriverConfig {
    public static String WEB_DRIVER_ID = "webdriver.gecko.driver";
    public static String WEB_DRIVER_PATH = "geckodriver";

    @Bean
    @Scope("prototype")
    public static WebDriver WebDriverConfig() {
        // Set WebDriver properties
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // WebDriver options
        FirefoxOptions options = new FirefoxOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("remote-debugging-port=9222");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-default-apps");

        WebDriver driver = new FirefoxDriver(options);
        return driver;
    }
}
