package food.backend.oauth.common.filter;

import food.backend.oauth.common.jwt.JwtProvider;
import food.backend.oauth.common.service.OAuthLoginService;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;


@Component
@RequiredArgsConstructor
@Order(1)
@Slf4j
public class AuthenticationFilter implements Filter {

    private static final String[] WHITE_LIST = {"/login", "/api/oauth/*", "/mall/*", "/foods/**"};
    private final JwtProvider jwtProvider;
    private final OAuthLoginService oAuthLoginService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info(">>>>>>>>> filter test ㅇㅅㅇ");
        log.info(">>>>>>>> url {}", httpRequest.getRequestURI());

        try {
            if (!isLoginCheckPath(httpRequest.getRequestURI())) {
                chain.doFilter(request, response);
                return;
            }

            String accessToken = getTokenFromCookie(httpRequest, "access_token");
            String refreshToken = getTokenFromCookie(httpRequest, "refresh_token");

            log.info(accessToken);
            log.info("is valid? {}", jwtProvider.validateJwt(accessToken));

            if (jwtProvider.validateJwt(accessToken)) {
                chain.doFilter(request, response);
                return;
            }

            log.info("access_token 유효하지 않음");
            if (jwtProvider.validateJwt(refreshToken)) {
                String email = jwtProvider.getEmailFromJwt(refreshToken);
                accessToken = jwtProvider.createJwt(email, 1000 * 60 * 60 * 24);

                oAuthLoginService.setupCookies(accessToken, refreshToken, httpResponse);
                log.info(email + " access token 재발급");
                chain.doFilter(request, response);
            } else {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (IOException ioException) {
            log.error("An error occurred in the filter: " + ioException.getMessage());
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private String getTokenFromCookie(HttpServletRequest request, String tokenName) {
        try {
            Cookie[] cookies = request.getCookies();

            return Arrays.stream(cookies)
                    .filter(cookie -> tokenName.equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("쿠키에서 토큰을 가져오는데 실패했습니다.");
        }
    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
