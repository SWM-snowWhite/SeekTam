package food.backend.batch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * ID : ST-C-100-J 작성자 : 임동훈(snowcrab382@naver.com) 버전 : 1.0.0 작성일 : 2023-10-20
 */
@Configuration
public class WebClientConfig {

    @Value("${openapi.url}")
    private String OPENAPI_URL;

    /**
     * WebClient Bean 등록 기본 URL을 설정한다
     *
     * @return WebClient
     */
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(OPENAPI_URL)
                .build();
    }
}
