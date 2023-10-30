package com.example.crawling.scraping.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.resps.Tuple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ScrapingDao {
    private final String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword;
    private String redisUrl;

    public ScrapingDao(
            @Value("${spring.datasource.url}") String jdbcUrl,
            @Value("${spring.datasource.username}") String jdbcUser,
            @Value("${spring.datasource.password}") String jdbcPassword,
            @Value("${spring.redis.url}") String redisUrl
    ) {
        this.jdbcUrl = jdbcUrl;
        this.jdbcUser = jdbcUser;
        this.jdbcPassword = jdbcPassword;
        this.redisUrl = redisUrl;
    }

    public void storeRdb(List<Map.Entry<String, Integer>> top10List) throws ClassNotFoundException, SQLException {

        try {
            // 현재 날짜 계산
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(now);

            // JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // MySQL 데이터베이스 연결
            Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

            // PreparedStatement를 사용하여 데이터 삽입
            String insertQuery = "INSERT INTO food_keyword_ranking(created_date, ranking, keyword, views) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            int rank = 1;

            // 각 항목을 순회하면서 삽입
            for (Map.Entry<String, Integer> entry : top10List) {
                String keyword = entry.getKey();
                int views = entry.getValue();

                // MySQL에 랭킹 저장
                preparedStatement.setString(1, formattedDate);
                preparedStatement.setInt(2, rank);
                preparedStatement.setString(3, keyword);
                preparedStatement.setInt(4, views);
                preparedStatement.executeUpdate();

                // 랭크 증가
                rank++;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
}

    public void storeRedis(List<Map.Entry<String, Integer>> top10List) {
        // Redis 연결
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), redisUrl);
        Jedis jedis = jedisPool.getResource();

        int rank = 1;

        // 각 항목을 순회하면서 삽입
        for (Map.Entry<String, Integer> entry : top10List) {
            String keyword = entry.getKey();

            // Redis에 랭킹 저장
            jedis.zadd("ranking", rank, keyword);

            // 다음 순위 증가
            rank++;
        }

        // 랭킹을 순서대로 뽑아오기
        List<Tuple> ranking = jedis.zrevrangeWithScores("ranking", 0, 20);

        // 결과 출력
        for (Tuple tuple : ranking) {
            String player = tuple.getElement();
            double score = tuple.getScore();
            System.out.println(player + ": " + score);
        }

        // 리소스 닫기
        jedis.close();
    }
}
