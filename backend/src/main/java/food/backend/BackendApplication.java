package food.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Application 클래스
 */
@SpringBootApplication
public class BackendApplication {

	/**
	 * Spring Boot Application 실행 메소드
	 * @param args 실행시 전달받은 인자
	 */
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
