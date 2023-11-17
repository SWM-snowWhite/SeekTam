package food.backend.batch.service;

import food.backend.batch.dto.FoodNutritionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.util.List;
import java.util.Map;

/**
 * ID : ST-C-100-J
 * 작성자 : 임동훈(snowcrab382@naver.com)
 * 버전 : 1.0.0
 * 작성일 : 2023-10-20
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class FoodDataApiService {

    @Value("${openapi.key}")
    private String serviceKey;

    private static final int initPage = 1;
    private static final int readPerPage = 200;
    private static int totalData;
    private final WebClient webClient;
    public final ObjectMapper objectMapper;


    /**
     * API 호출을 통해 전체 데이터의 개수를 가져온다.
     * 가져온 데이터 개수를 변수 totalData에 저장한다.
     */
    public void setTotalData() {
        log.info("setTotalData() 호출");
        totalData = extractTotalDataSize();
    }

    /**
     * 전체 데이터 개수와 1회 조회 시 가져올 데이터 개수를 나누어 전체 chunk 반복횟수를 계산한다
     * @return 전체 chunk 반복횟수
     */
    public int getPageSize() {
        log.info("getPageSize() 호출");
        return (totalData / readPerPage) + 1;
    }

    /**
     * API 호출을 통해 전체 데이터 개수를 가져온다.
     * 이 경우에 첫페이지만 호출하면 되므로 pageNo = initPage로 고정한다.
     * @return 전체 데이터 개수
     */
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

    /**
     * API 호출을 통해 음식 데이터를 가져온다.
     * @param pageNo 현재 실행되는 페이지 번호
     * @return API 호출 결과(JSON)
     */
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

    /**
     * API 호출 결과를 DTO에 바인딩한다.
     * DTO에 바인딩된 음식 데이터를 readPerPage만큼 List에 저장 후 반환한다.
     * @param response API 호출 결과(JSON)
     * @return DTO에 바인딩된 음식 데이터 List
     */
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
