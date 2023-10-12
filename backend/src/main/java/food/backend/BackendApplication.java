package food.backend;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring Boot Application 클래스
 */
@EnableScheduling
@EnableBatchProcessing
@SpringBootApplication
@EnableCaching
public class BackendApplication {

	/**
	 * Spring Boot Application 실행 메소드
	 * @param args 실행시 전달받은 인자
	 */
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
