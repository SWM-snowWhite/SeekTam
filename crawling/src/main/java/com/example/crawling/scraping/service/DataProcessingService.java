package com.example.crawling.scraping.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DataProcessingService {
    private static HashMap<String, Integer> keywordRank = new HashMap<>();
    private static List<String> uselessKeywordList = Arrays.asList(
            "쿠팡",
            "컬리",
            "이마트",
            "롯데마트",
            "홈플러스",
            "배송",
            "할인",
            "쿠폰",
            "인분",
            "선택"
    );

    /**
     *
     * @param keywordList
     * 1. keywordList에서 1글자 이하인 단어를 제거한다.
     * 2. keywordList에서 영어 또는 숫자로만 이루어진 단어를 제거한다.
     * 3. keywordList에서 uselessKeywordList에 포함된 단어를 제거한다.
     * @return List<String>
     */
    public static ArrayList<String> filterUselessKeyword(ArrayList<String> keywordList) {
        List<String> filteredList = keywordList.stream()
                .filter(str -> str.length() > 1)
                .filter(str -> !isAlphaNumeric(str))
                .filter(str -> !uselessKeywordList.contains(str))
                .collect(Collectors.toList());

        return new ArrayList<>(filteredList);
    };

    public static boolean isAlphaNumeric(String str) {
        // 문자열이 영어 또는 숫자로만 이루어져 있는지 확인하는 메서드
        return str.matches("[a-zA-Z0-9]+");
    }

    /**
     * keywordList에서 상위 10개의 단어를 추출한다.
     * @param keywordList
     * @return List<String>
     */
    public static List<Map.Entry<String, Integer>> remainTop10List(List<String> keywordList) {

        keywordList.forEach(word -> keywordRank.put(word, keywordRank.getOrDefault(word, 0) + 1));
        List<Map.Entry<String, Integer>> sortedList =
            keywordRank.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toList());

        ArrayList<Map.Entry<String, Integer>> sortedArrayList = new ArrayList<>(sortedList);
        List<Map.Entry<String, Integer>> top10List = sortedArrayList.subList(0, Math.min(10, sortedArrayList.size()));
        return top10List;
    }
}
