package com.example.crawling.scraping.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

@Service
@Slf4j
public class ElasticSearchService implements ElasticSearch{
    ArrayList<String> seperatedKeywordList = new ArrayList<>();

    private final String ELASTIC_NORI_URL;

    public ElasticSearchService(
            @Value("${spring.elastic.url}") String elasticUrl) {
        this.ELASTIC_NORI_URL = elasticUrl + "/_analyze?pretty";
    }

//    @Value("${application.encodedCode}")
    private static String encodedCode = "Basic ZWxhc3RpYzo9ME1CWnMteitXMUtvc1VJWndSeA==";

    @Override
    public ArrayList<String> separateMorpheme(List<String> keywordList) {

        log.info("****************** 엘라스틱서치 형태소 분석 ************************" + encodedCode);

        try {
            keywordList.stream().forEach(keyword -> {
                Map<String, Object> result = getReqeust(ELASTIC_NORI_URL, keyword);
                ArrayList<HashMap<String, String>> tokensInfo = (ArrayList<HashMap<String, String>>) result.get("tokens");

                tokensInfo.forEach(tokenInfo -> {
                    String word = tokenInfo.get("token");
                    seperatedKeywordList.add(word);

                });
            });
            return seperatedKeywordList;
        } catch (Exception e) {
            e.printStackTrace();
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
                    .header(HttpHeaders.AUTHORIZATION,encodedCode)
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
