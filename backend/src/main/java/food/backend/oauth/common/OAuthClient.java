package food.backend.oauth.common;


public interface OAuthClient {

    TokenInfo requestTokenInfo(LoginParams loginParams);
    OAuthType getOAuthType();

    String requestAccessToken(String accessToken);
    String requestRefreshToken(String refreshToken);
}
