package food.backend.oauth;


import food.backend.oauth.jwt.JwtProvider;
import org.springframework.stereotype.Component;


public interface OAuthClient {

    TokenInfo requestTokenInfo(LoginParams loginParams);
    OAuthType getOAuthType();

    String requestAccessToken(String accessToken);
    String requestRefreshToken(String refreshToken);
}
