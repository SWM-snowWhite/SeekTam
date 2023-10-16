package com.example.crawling.scraping.service;

import java.util.List;

public interface Scraping {
    List<String> crawling(String className, List<String> urls);
}
