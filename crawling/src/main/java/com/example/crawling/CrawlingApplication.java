package com.example.crawling;

import co.elastic.clients.elasticsearch.nodes.Http;
import co.elastic.clients.util.DateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpHead;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.json.JsonParser;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@EnableScheduling
@EnableBatchProcessing
@SpringBootApplication
@Slf4j
public class CrawlingApplication {
    enum ShoppingMall {
        COU, KUR, EMA, HOM, LOT
    }

    // Driver configuration
    public static String WEB_DRIVER_ID = "webdriver.gecko.driver";
    public static String WEB_DRIVER_PATH = "geckodriver";

    public static void main(String[] args) throws IOException {
        HashMap<ShoppingMall, HashMap<String, Object>> crawlingSiteInfo = new HashMap<>();

        // Define website crawling information
//        crawlingSiteInfo.put(ShoppingMall.COU, getCoupangInfo());
//        crawlingSiteInfo.put(ShoppingMall.KUR, getKurlyInfo());
//        crawlingSiteInfo.put(ShoppingMall.EMA, getEmartInfo());
//        crawlingSiteInfo.put(ShoppingMall.HOM, getHomeplusInfo());
        crawlingSiteInfo.put(ShoppingMall.LOT, getLotteInfo());

        ArrayList<String> keywordList = new ArrayList<>();
        HashMap<String, Integer> keywordRank = new HashMap<>();

        try {
            for (Map.Entry<ShoppingMall, HashMap<String, Object>> entry : crawlingSiteInfo.entrySet()) {
                ArrayList<String> data = crawling(entry.getKey(), entry.getValue());
                log.info("******************************************");
                log.info("크롤링한 데이터 갯수: " + data.size());
                keywordList.addAll(data);
            }
        } catch (IOException e) {
            log.error("크롤링 중 오류 발생: " + e.getMessage());
        }

        log.info("******************************************");
        log.info("keywordList:::::" + keywordList);
        log.info("데이터 총 개수: " + keywordList.size());

        String apiUrl = "http://localhost:9200/_analyze?pretty";
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

        log.info("------------------------------------------");
        for (Map.Entry<String, Integer> entry : sortedArrayList) {
            log.info(entry.getKey() + " : " + entry.getValue());
        }
        log.info("------------------------------------------");

        String jdbcUrl = "jdbc:mysql://localhost:3306/seektam_test";
        String jdbcUser = "root";
        String jdbcPassword = "";

        List<Map.Entry<String, Integer>> top10List = sortedArrayList.subList(0, Math.min(10, sortedArrayList.size()));
        try {
            // JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Redis 연결
            JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost:6379");
            Jedis jedis = jedisPool.getResource();

            // 현재 날짜 계산
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(now);

            // MySQL 데이터베이스 연결
            Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

            // PreparedStatement를 사용하여 데이터 삽입
            String insertQuery = "INSERT INTO food_keyword_ranking VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            int rank = 1;

            // 각 항목을 순회하면서 삽입
            for (Map.Entry<String, Integer> entry : top10List) {
                String keyword = entry.getKey();
                int hits = entry.getValue();

                // Redis에 랭킹 저장
                jedis.zadd("ranking", rank, keyword);

                // MySQL에 랭킹 저장
                preparedStatement.setString(1, formattedDate);
                preparedStatement.setInt(2, rank);
                preparedStatement.setString(3, keyword);
                preparedStatement.setInt(4, hits);
                preparedStatement.executeUpdate();

                // 다음 순위 증가
                rank++;
            }

            // 리소스 닫기
            preparedStatement.close();
            connection.close();
            jedis.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        JedisPool pool = new JedisPool("localhost", 6379);
//
//        try (Jedis jedis = pool.getResource()) {
//            jedis.set("foo", "bar");
//            log.info(jedis.get("foo"));
//
//            for (Map.Entry<String, Integer> entry : sortedArrayList) {
//                jedis.set(entry.getKey(), String.valueOf(entry.getValue()));
//            }
//            log.info("------------------------------");
//            log.info("------------------------------");
//            Map<String, String> hash = new HashMap<>();
//            hash.put("name", "John");
//            hash.put("surname", "Smith");
//            hash.put("company", "Redis");
//            hash.put("age", "29");
//            jedis.hset("user-session:123", hash);
//            log.info(jedis.hgetAll("user-session:123").toString());
//        }

    }

    private static Map<String, Object> getReqeust(String baseUrl, String keywordList ) {

        Map<String, String> bodyData = new HashMap<>();
        bodyData.put("analyzer", "nori");
        bodyData.put("text", keywordList);

        log.info("bodyData" + bodyData.toString());
        try {
            Map<String, Object> response = WebClient.create()
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
//            log.info("-----------------------------------");
//            log.info(response.toString());
//            log.info("-----------------------------------");
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

    private static HashMap<String, Object> getCoupangInfo() {
        HashMap<String, Object> coupangInfo = new HashMap<>();
        coupangInfo.put("url", Arrays.asList(
                "https://www.coupang.com/np/categories/393760?listSize=120&brand=&offerCondition=&filterType=&isPriceRange=false&minPrice=&maxPrice=&page=1&channel=user&fromComponent=N&selectedPlpKeepFilter=&sorter=bestAsc&filter=&rating=0",
                "https://www.coupang.com/np/categories/393760?listSize=120&brand=&offerCondition=&filterType=&isPriceRange=false&minPrice=&maxPrice=&page=2&channel=user&fromComponent=N&selectedPlpKeepFilter=&sorter=bestAsc&filter=&rating=0"
        ));
        coupangInfo.put("className", "name");
        return coupangInfo;
    }

    private static HashMap<String, Object> getKurlyInfo() {
        HashMap<String, Object> kurlyInfo = new HashMap<>();
        kurlyInfo.put("url", Arrays.asList(
                "https://www.kurly.com/collections/market-best?page=1&per_page=96&sorted_type=4",
                "https://www.kurly.com/collections/market-best?page=2&per_page=96&sorted_type=4",
                "https://www.kurly.com/collections/market-best?page=3&per_page=96&sorted_type=4"
        ));
        kurlyInfo.put("className", "css-1dry2r1");
        return kurlyInfo;
    }

    private static HashMap<String, Object> getEmartInfo() {
        HashMap<String, Object> emartInfo = new HashMap<>();
        emartInfo.put("url", Arrays.asList("https://emart.ssg.com/best/main.ssg"));
        emartInfo.put("className", "mnemitem_goods_tit");
        return emartInfo;
    }

    private static HashMap<String, Object> getHomeplusInfo() {
        HashMap<String, Object> homeplusInfo = new HashMap<>();
        homeplusInfo.put("url", Arrays.asList("https://front.homeplus.co.kr/best?gnbNo=3"));
        homeplusInfo.put("className", "css-12cdo53-defaultStyle-Typography-ellips");
        return homeplusInfo;
    }

    private static HashMap<String, Object> getLotteInfo() {
        HashMap<String, Object> lotteInfo = new HashMap<>();
        lotteInfo.put("url", Arrays.asList("https://www.lotteon.com/p/display/shop/seltDpShop/30308?callType=menu"));
        lotteInfo.put("className", "s-goods-title");
        return lotteInfo;
    }

    private static ArrayList<String> crawling(ShoppingMall mallName, HashMap<String, Object> siteInfo) throws IOException {
        log.info("siteInfo ::::: " + siteInfo);
        List<String> urlList = (List<String>) siteInfo.get("url");
        String className = (String) siteInfo.get("className");

        log.info("mallName ::::: " + mallName);
        log.info("urlList ::::: " + urlList);
        log.info("className ::::: " + className);

        // Set WebDriver properties
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // WebDriver options
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-default-apps");

        WebDriver driver = new FirefoxDriver(options);

        log.info("--------------------------------------------------------------------");

        ArrayList<String> keywordList = new ArrayList<>();
        urlList.forEach(url -> {
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            List<WebElement> elements = driver.findElements(By.className(className));
            for (WebElement element : elements) {
                log.info("content.data() ::::" + element.getText());
                keywordList.add(element.getText());
            }
            log.info("--------------------------------------------------------------------");
            log.info("elements" + elements.size() + "");
        });
        return keywordList;
    }
}
