package food.backend.oauth.kakao;

import food.backend.oauth.LoginParams;
import food.backend.oauth.OAuthType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoLoginParams implements LoginParams {

    private String authorizationCode;
    @Override
    public OAuthType getOAuthType() {
        return OAuthType.KAKAO;
    }
}
