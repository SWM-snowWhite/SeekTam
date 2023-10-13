package com.example.crawling.scraping.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScrapingService implements Scraping {
    private final WebDriver webDriver;

    @Override
    public List<String> crawling(String className, List<String> urls){
        ArrayList<String> keywordList = new ArrayList<>();

        urls.forEach(url -> {
            webDriver.get(url);
            webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            List<WebElement> elements = webDriver.findElements(By.className(className));
            for (WebElement element : elements) {
                keywordList.add(element.getText());
            }
        });

        log.info("****************** 크롤링 ************************");
        log.info("데이터 총 개수: " + keywordList.size());

        return keywordList;
    }
}
