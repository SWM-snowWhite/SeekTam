//package food.backend.member;
//
//import food.backend.member.service.MemberService;
//import food.backend.oauth.common.LoginParams;
//import food.backend.oauth.common.OAuthClient;
//import food.backend.oauth.common.OAuthType;
//import food.backend.oauth.common.service.OAuthLoginService;
//import food.backend.oauth.kakao.KakaoLoginParams;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import javax.servlet.http.HttpServletResponse;
//
//public class MemberServiceTest {
//
//    @InjectMocks
//    private MemberService memberService;
//
//    @Mock
//    private OAuthClient oAuthClient;
//
//    @Mock
//    private OAuthLoginService oAuthLoginService;
//
//    @Mock
//    private MemberRepository memberRepository;
//
//    @Mock
//    private HttpServletResponse response;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//
////    @Test
////    public void 카카오_로그인_신규회원() {
////        // Given
////        LoginParams loginParams = new LoginParams(OAuthType.KAKAO);
////
////        OAuthClient oAuthClient = Oauth.get(loginParams.getOAuthType());
////        TokenInfo mockTokenInfo = new TokenInfo(); // 필요한 정보를 설정하세요.
////        KakaoUserInfo mockKakaoUserInfo = new KakaoUserInfo(); // 필요한 정보를 설정하세요.
////
////        when(oAuthClientHandler.get(any())).thenReturn(oAuthClient);
////        when(oAuthClient.requestTokenInfo(loginParams)).thenReturn(mockTokenInfo);
////        when(oAuthClient.requestKaKaoUserInfo(anyString())).thenReturn(mockKakaoUserInfo);
////        when(memberRepository.findByEmail(anyString())).thenReturn(Optional.empty());
////
////        // When
////        Member result = oAuthLoginService.login(loginParams, response);
////
////        // Then
////        // 새로운 회원이 등록되었는지 확인하세요. 예를 들면:
////        verify(memberRepository).save(any(Member.class));
////    }
////
////    @Test
////    public void 카카오_로그인_회원존재() {
////        // Given
////        LoginParams loginParams = new LoginParams(); // 필요한 정보를 설정하세요.
////        TokenInfo mockTokenInfo = new TokenInfo(); // 필요한 정보를 설정하세요.
////        KakaoUserInfo mockKakaoUserInfo = new KakaoUserInfo(); // 필요한 정보를 설정하세요.
////        Member mockMember = new Member(); // 필요한 정보를 설정하세요.
////
////        when(oAuthClientHandler.get(any())).thenReturn(oAuthClient);
////        when(oAuthClient.requestTokenInfo(loginParams)).thenReturn(mockTokenInfo);
////        when(oAuthClient.requestKaKaoUserInfo(anyString())).thenReturn(mockKakaoUserInfo);
////        when(memberRepository.findByEmail(anyString())).thenReturn(Optional.of(mockMember));
////
////        // When
////        Member result = memberService.login(loginParams, response);
////
////        // Then
////        // 이미 존재하는 회원 정보를 반환하는지 확인하세요.
////        assertSame(result, mockMember);
////    }
//}