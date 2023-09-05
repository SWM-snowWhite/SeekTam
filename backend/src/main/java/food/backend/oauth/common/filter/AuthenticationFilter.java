package food.backend.oauth.common.filter;

import food.backend.oauth.common.jwt.JwtProvider;
import food.backend.oauth.common.service.OAuthLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


@Component
@RequiredArgsConstructor
@Order(1)
@Slf4j
public class AuthenticationFilter implements Filter {

    private static final String[] WHITE_LIST = {"/", "/keyword/*", "/login", "/logout", "/api/oauth/*"};
    private final JwtProvider jwtProvider;
    private final OAuthLoginService oAuthLoginService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info(">>>>>>>>> filter test ㅇㅅㅇ");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info(">>>>>>>> url {}", httpRequest.getRequestURI());
        log.info(">>>>>>>> is equal? : {}", !isLoginCheckPath(httpRequest.getRequestURI()));
        if (!isLoginCheckPath(httpRequest.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        String accessToken = getTokenFromCookie(httpRequest, "access_token");
        String refreshToken = getTokenFromCookie(httpRequest, "refresh_token");

        try {
            if (jwtProvider.validateJwt(accessToken)) {
                chain.doFilter(request, response);
                return;
            }

            if (jwtProvider.validateJwt(refreshToken)) {
                String memberId = jwtProvider.getMemberIdFromJwt(refreshToken);
                accessToken = jwtProvider.createJwt(memberId, 50000);

                oAuthLoginService.setupCookies(accessToken, refreshToken, httpResponse);

                chain.doFilter(request, response);
                return;
            }


            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        } catch (Exception e) {

        }


    }

    private String getTokenFromCookie(HttpServletRequest request, String tokenName) {
        Cookie[] cookies = request.getCookies();

        return Arrays.stream(cookies)
                .filter(cookie -> tokenName.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
