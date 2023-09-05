package food.backend.oauth.kakao;

import food.backend.oauth.common.LoginParams;
import food.backend.oauth.common.OAuthClient;
import food.backend.oauth.common.OAuthType;
import food.backend.oauth.common.TokenInfo;
import food.backend.oauth.common.jwt.JwtProvider;
import food.backend.oauth.kakao.entity.KakaoToken;
import food.backend.oauth.kakao.entity.KakaoUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Component
@RequiredArgsConstructor
public class OAuthKakaoClient implements OAuthClient {

    private static final String GRANT_TYPE = "authorization_code";
    private final RestTemplate restTemplate;
    private final JwtProvider jwtProvider;

    @Value("${oauth.kakao.client_id}")
    private String clientId;

    @Value("${oauth.kakao.redirect_uri}")
    private String redirectURI;

    @Value("${oauth.kakao.auth_uri}")
    private String authURI;

    @Value("${oauth.kakao.info_uri}")
    private String infoURI;

    @Value("${jwt.accessExpirationTime}")
    private int accessExpirationTime;

    @Value("${jwt.refreshExpirationTime}")
    private int refreshExpirationTime;
    @Override
    public OAuthType getOAuthType() {
        return OAuthType.KAKAO;
    }

    @Override
    public TokenInfo requestTokenInfo(LoginParams loginParams) {
        String tokenProviderURI = authURI + "/oauth/token";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> httpBody = makeHttpBody(loginParams);

        HttpEntity<?> request = new HttpEntity<>(httpBody, httpHeaders);

        KakaoToken kakaoToken = restTemplate.postForObject(tokenProviderURI, request, KakaoToken.class);

        return TokenInfo.builder()
                .accessToken(kakaoToken.getAccessToken())
                .refreshToken(kakaoToken.getRefreshToken())
                .build();
    }


    @Override
    public String requestAccessToken(String accessToken) {
        String tokenInfoURI = infoURI + "/v2/user/me";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        HttpEntity<?> request = new HttpEntity<>(new LinkedMultiValueMap<>(), httpHeaders);

        KakaoUserInfo kakaoUserInfo = restTemplate.postForObject(tokenInfoURI, request, KakaoUserInfo.class);

        return makeJwt(kakaoUserInfo.getMemberId().toString(), accessExpirationTime);
    }

    // 주석 + 예시 데이터 추가

    @Override
    public String requestRefreshToken(String refreshToken) {
        return makeJwt(refreshToken, refreshExpirationTime);
    }

    public String makeJwt(String subject, int expirationTime) {
        return jwtProvider.createJwt(subject + " " + OAuthType.KAKAO, expirationTime);
    }
    public MultiValueMap<String, String> makeHttpBody(LoginParams loginParams) {
        KakaoLoginParams kakaoLoginParams = (KakaoLoginParams)loginParams;

        MultiValueMap<String, String> httpBody = new LinkedMultiValueMap<>();

        httpBody.add("code", kakaoLoginParams.getAuthorizationCode());
        httpBody.add("client_id", clientId);
        httpBody.add("grant_type", GRANT_TYPE);
        httpBody.add("redirect_uri", redirectURI);

        return httpBody;
    }
}
