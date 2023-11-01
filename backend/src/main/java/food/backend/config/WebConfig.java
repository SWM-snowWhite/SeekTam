package food.backend.config;

import food.backend.member.common.MemberDtoArgumentResolver;
import food.backend.oauth.common.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * CORS 설정을 위한 클래스<br>
 * CORS : Cross-Origin Resource Sharing<br>
 * 다른 도메인에서 리소스를 요청할 수 있도록 허용하는 설정<br>
 * 현재 프로젝트에서는 프론트엔드와 백엔드가 다른 도메인에서 실행되기 때문에 CORS 설정이 필요<br>
 * 프론트엔드 도메인 : http://localhost:3000, http://www.seektam.com<br>
 * 백엔드 도메인 : http://localhost:8080, http://www.seektam.link
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final JwtProvider jwtProvider;
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:3000",
                        "http://localhost:8080",
                        "http://www.seektam.com",
                        "https://www.seektam.link",
                        "https://www.seektam.com"
                )
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new MemberDtoArgumentResolver(jwtProvider));
    }
}
