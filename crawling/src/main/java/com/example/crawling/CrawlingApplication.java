package com.example.crawling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
    
@EnableScheduling
@SpringBootApplication
public class CrawlingApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrawlingApplication.class, args);
    }
}
