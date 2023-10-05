package com.example.crawling.scraping.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ElasticSearch {
    List<Map.Entry<String, Integer>> separateMorpheme(List<String> keywordList);
}
