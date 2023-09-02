package food.backend.oauth.service;

import food.backend.oauth.LoginParams;
import food.backend.oauth.OAuthClient;
import food.backend.oauth.OAuthType;
import food.backend.oauth.TokenInfo;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OAuthLoginService {

    private final Map<OAuthType, OAuthClient> OAuthClientHandler;

    public OAuthLoginService(List<OAuthClient> clientList) {
        this.OAuthClientHandler = clientList.stream().collect(
                Collectors.toUnmodifiableMap(OAuthClient::getOAuthType, Function.identity()));


        //OAuthClientHandler = Map.of(OAuthType.KAKAO, new OAuthKakaoClient(new RestTemplate()));
    }

    public void login(LoginParams loginParams, HttpServletResponse response) {

        OAuthClient oAuthClient = OAuthClientHandler.get(loginParams.getOAuthType());
        TokenInfo tokenInfo = oAuthClient.requestTokenInfo(loginParams);

        setupCookies(tokenInfo, response);
    }

    private void setupCookies(TokenInfo tokenInfo, HttpServletResponse response) {
        Cookie accessTokenCookie = new Cookie("access_token", tokenInfo.getAccessToken());
        Cookie refreshTokenCookie = new Cookie("refresh_token", tokenInfo.getRefreshToken());

        accessTokenCookie.setMaxAge(3600);
        refreshTokenCookie.setMaxAge(3600);

        accessTokenCookie.setPath("/");
        refreshTokenCookie.setPath("/");

        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);
    }
}
