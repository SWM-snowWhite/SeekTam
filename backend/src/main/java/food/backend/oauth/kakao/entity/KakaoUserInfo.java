package food.backend.oauth.kakao.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoUserInfo {


    @JsonProperty("id")
    private Long id;
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KakaoAccount {
        @JsonProperty("profile")
        private KakaoProfile profile;
        @JsonProperty("email")
        private String email;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KakaoProfile {
        @JsonProperty("nickname")
        private String nickname;
        @JsonProperty("thumbnail_image_url")
        private String profileImageUrl;
    }

}
