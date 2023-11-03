package food.backend.oauth.common;


import food.backend.oauth.kakao.entity.KakaoUserInfo;

public interface OAuthClient {

    TokenInfo requestTokenInfo(LoginParams loginParams);
    OAuthType getOAuthType();

    KakaoUserInfo requestKaKaoUserInfo(String accessToken);

    String requestAccessToken(String accessToken);
    String requestRefreshToken(String refreshToken);
}
