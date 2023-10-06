package com.example.crawling.scraping.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScrapingScheduler {

    private final CoupangService coupangService;
    private final KurlyService kurlyService;
    private final EmartService emartService;
    private final LottemartService lottemartService;
    private final HomeplusService homeplusService;

    // 매 분마다 시작 (테스트)
    @Scheduled(fixedDelay = 1000)
    public void scarpingJobScheduler() throws SQLException, ClassNotFoundException {
        log.info("스케쥴러 시작" + new Date().toString());

        try {
            // 에러
//            coupangService.crawling();

            // 정상 작동
            kurlyService.crawling();
//            emartService.crawling();
//            lottemartService.crawling();
//            homeplusService.crawling();
        } catch (Exception e) {
            log.error("스케줄러 에러 발생", e);
        }
    }
}
