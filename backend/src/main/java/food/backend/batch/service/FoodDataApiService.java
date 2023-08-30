package food.backend.batch.service;

import food.backend.batch.dto.FoodNutritionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class FoodDataApiService {

    private static final int initPage = 1;
    private static final int readPerPage = 200;
    private final String serviceKey = "SxNmD8Ax8VbMjc4NFG2oTMXRV3xxkFOYYABFd4lq9Rgfg17R0KTniwy6Ya0EaLuKmdV32Dd9swbx/T2boSFP6Q==";
    private static int totalData;
    private final WebClient webClient;
    public final ObjectMapper objectMapper;


    public void setTotalData() {
        log.info("setTotalData() 호출");
        totalData = extractTotalDataSize();
    }

    public int getPageSize() {
        log.info("getPageSize() 호출");
        return (totalData / readPerPage) + 1;
    }

    public int extractTotalDataSize() {
        log.info("extractTotalDataSize() 호출");
        String response = getFoodDataResponse(initPage);
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return jsonNode.at("/response/body/totalCount").asInt();
    }

    public String getFoodDataResponse(int pageNo) {
        String foodDataResponse = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("serviceKey", serviceKey)
                        .queryParam("pageNo", pageNo)
                        .queryParam("numOfRows", readPerPage)
                        .queryParam("type", "json")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(foodDataResponse);
        } catch (JsonProcessingException
                |WebClientRequestException e) {
            log.info(">>>>>>API 호출결과 관련예외발생, API 호출결과 : {}", foodDataResponse);
            throw new RuntimeException("API 호출결과가 정상적이지 않음");
        }

        JsonNode resultCode = jsonNode.at("/response/header/resultCode");
        if (!resultCode.asText().equals("00")) {
            log.info(">>>>>>API 호출결과 관련예외발생, API 호출결과 : {}", foodDataResponse);
            throw new RuntimeException("API 호출결과코드가 정상적이지 않음");
        }

        return foodDataResponse;

    }

    public List<FoodNutritionDto> bindFoodDataToDto(String response) {

        Map<String, Object> responseObject = null;
        try {
            responseObject = objectMapper.readValue(response, new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            log.info(">>>>>>API 호출관련 예외 발생, API 호출결과 : {}", response);
            throw new RuntimeException(e);
        }
        Map<String, Object> responseProperty = (Map<String, Object>) responseObject.get("response");
        Map<String, Object> bodyProperty = (Map<String, Object>) responseProperty.get("body");
        List<Map<String, Object>> itemsProperty = (List<Map<String, Object>>) bodyProperty.get("items");

        List<FoodNutritionDto> foodData = objectMapper.convertValue(itemsProperty, new TypeReference<>() {});
        return foodData;
    }


}
