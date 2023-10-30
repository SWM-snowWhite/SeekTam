package food.backend.oauth.common.controller;


import food.backend.member.Member;
import food.backend.oauth.kakao.KakaoLoginParams;
import food.backend.oauth.common.service.OAuthLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/oauth")
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthLoginService oAuthLoginService;

    @PostMapping("/kakao")
    public ResponseEntity<Member> loginKakao(@RequestBody KakaoLoginParams kakaoLoginParams, HttpServletResponse response) {
        System.out.println("kakaoLoginParams = " + kakaoLoginParams);
        return ResponseEntity.ok(oAuthLoginService.login(kakaoLoginParams, response));
    }
}


