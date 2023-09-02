package food.backend.oauth.kakao;

import food.backend.oauth.LoginParams;
import food.backend.oauth.OAuthClient;
import food.backend.oauth.OAuthType;
import food.backend.oauth.TokenInfo;
import food.backend.oauth.entity.KakaoToken;
import food.backend.oauth.jwt.JwtProvider;
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

        return kakaoToken.of();
    }

    @Override
    public String requestAccessToken(String accessToken) {
        String tokenInfoURI = infoURI + "/v2/user/me";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        HttpEntity<?> request = new HttpEntity<>(new LinkedMultiValueMap<>(), httpHeaders);

        Long memberId = restTemplate.postForObject(tokenInfoURI, request, Long.class);

        return makeJwt(memberId.toString());
    }

    @Override
    public String requestRefreshToken(String refreshToken) {
        return makeJwt(refreshToken);
    }

    public String makeJwt(String subject) {
        return jwtProvider.createJwt(subject);
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
