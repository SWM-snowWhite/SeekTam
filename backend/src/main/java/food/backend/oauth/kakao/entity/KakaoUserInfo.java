package food.backend.oauth.kakao.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class KakaoUserInfo {

    @JsonProperty("id")
    private Long memberId;


}
