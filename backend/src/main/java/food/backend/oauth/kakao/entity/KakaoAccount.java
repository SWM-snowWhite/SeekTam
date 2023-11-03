package food.backend.oauth.kakao.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoAccount {

    @JsonProperty("id")
    private Long memberId;
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;
}