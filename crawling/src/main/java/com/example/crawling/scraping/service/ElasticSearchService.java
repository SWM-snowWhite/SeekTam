package com.example.crawling.scraping.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ElasticSearchService implements ElasticSearch{
    ArrayList<String> keywordList = new ArrayList<>();
    HashMap<String, Integer> keywordRank = new HashMap<>();

    String apiUrl = "http://localhost:9200/_analyze?pretty";

    @Override
    public List<Map.Entry<String, Integer>> separateMorpheme(List<String> keywordList) {
        try {
            keywordList.stream().forEach(keyword -> {
                Map<String, Object> result = getReqeust(apiUrl, keyword);
                ArrayList<HashMap<String, String>> tokensInfo = (ArrayList<HashMap<String, String>>) result.get("tokens");

                tokensInfo.forEach(tokenInfo -> {
                    String word = tokenInfo.get("token");
                    keywordRank.put(word, keywordRank.getOrDefault(word, 0) + 1);
                });
            });

            List<Map.Entry<String, Integer>> sortedList = keywordRank.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Integer> comparingByValue().reversed())
                    .collect(Collectors.toList());

            ArrayList<Map.Entry<String, Integer>> sortedArrayList = new ArrayList<>(sortedList);
            List<Map.Entry<String, Integer>> top10List = sortedArrayList.subList(0, Math.min(10, sortedArrayList.size()));
            return top10List;
        } catch (Exception e) {
            return null;
        }
    }

    private static Map<String, Object> getReqeust(String baseUrl, String keywordList ) {

        Map<String, String> bodyData = new HashMap<>();
        bodyData.put("analyzer", "nori");
        bodyData.put("text", keywordList);

        log.info("bodyData" + bodyData.toString());
        try {
            Map response = WebClient.create()
                    .post()
                    .uri(baseUrl)
                    .header(HttpHeaders.ACCEPT, "*/*")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(bodyData))
                    .retrieve()
                    .onStatus(HttpStatus::isError, res -> {
                        logTraceResponse(res);
                        return null;
                    })
                    .bodyToFlux(Map.class)
                    .blockLast();
            return response;
        } catch(Exception e) {
            log.error("error " + e.getMessage());
        }
        return null;
    }

    public static void logTraceResponse(ClientResponse response) {
        log.info("Response status: {}" + response.statusCode());
        log.info("Response headers: {}"+  response.headers().asHttpHeaders());
        response.bodyToMono(String.class)
                .publishOn(Schedulers.elastic())
                .subscribe(body -> log.info("Response body: {}" + body));
    }
}
