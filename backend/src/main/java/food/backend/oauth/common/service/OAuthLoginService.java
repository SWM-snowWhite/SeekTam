package food.backend.oauth.common.service;

import food.backend.member.Member;
import food.backend.member.MemberRepository;
import food.backend.member.service.MemberService;
import food.backend.oauth.common.LoginParams;
import food.backend.oauth.common.OAuthClient;
import food.backend.oauth.common.OAuthType;
import food.backend.oauth.common.TokenInfo;
import food.backend.oauth.kakao.entity.KakaoUserInfo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OAuthLoginService {

    private final Map<OAuthType, OAuthClient> OAuthClientHandler;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public OAuthLoginService(List<OAuthClient> clientList, MemberService memberService, MemberRepository memberRepository) {
        this.OAuthClientHandler = clientList.stream().collect(
                Collectors.toUnmodifiableMap(OAuthClient::getOAuthType, Function.identity()));
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    public Optional<OAuthClient> getOAuthClientFromOAuthType(OAuthType targetOAuthType) {
        return OAuthClientHandler.entrySet()
                .stream()
                .filter(entry -> targetOAuthType == entry.getKey())
                .map(Map.Entry::getValue)
                .findFirst();
    }

    @Operation(summary = "OAuth 로그인 메서드", description = "OAuth를 통한 사용자 로그인을 처리합니다.")
    public Member login(LoginParams loginParams, HttpServletResponse response) {
        OAuthClient oAuthClient = OAuthClientHandler.get(loginParams.getOAuthType());
        TokenInfo tokenInfo = oAuthClient.requestTokenInfo(loginParams);

        // Kakao API를 통해 사용자 정보를 가져온다.
        KakaoUserInfo kakaoUserInfo = oAuthClient.requestKaKaoUserInfo(tokenInfo.getAccessToken());

        // 토큰 정보를 통해 새로운 토큰을 발급 받아 쿠키에 저장
        String accessToken = oAuthClient.requestAccessToken(tokenInfo.getAccessToken());
        String refreshToken = oAuthClient.requestRefreshToken(tokenInfo.getRefreshToken());
        setupCookies(accessToken, refreshToken, response);

        // 회원 존재 여부에 따라 회원가입 또는 로그인
        return memberRepository.findByEmail(kakaoUserInfo.getKakaoAccount().getEmail())
                .orElseGet(() -> registerNewMember(kakaoUserInfo));
    }

    private Member registerNewMember(KakaoUserInfo kakaoUserInfo) {
        Member newMember = Member.builder()
                .id(kakaoUserInfo.getId())
                .email(kakaoUserInfo.getKakaoAccount().getEmail())
                .profileImageUrl(kakaoUserInfo.getKakaoAccount().getProfile().getProfileImageUrl())
                .nickname(kakaoUserInfo.getKakaoAccount().getProfile().getNickname())
                .applyType("1")
                .build();
        return memberService.signup(newMember);
    }

    public void setupCookies(String accessToken, String refreshToken, HttpServletResponse response) {
        Cookie accessTokenCookie = new Cookie("access_token", accessToken);
        Cookie refreshTokenCookie = new Cookie("refresh_token", refreshToken);

        accessTokenCookie.setMaxAge(3600);
        refreshTokenCookie.setMaxAge(3600);

        accessTokenCookie.setPath("/");
        refreshTokenCookie.setPath("/");

        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);
    }
}
