package com.example.crawling.scraping.service;

import java.util.List;

public interface ElasticSearch {
    List<String> separateMorpheme(List<String> keywordList);
}
