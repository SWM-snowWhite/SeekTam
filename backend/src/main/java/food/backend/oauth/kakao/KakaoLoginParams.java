package food.backend.oauth.kakao;

import food.backend.oauth.common.LoginParams;
import food.backend.oauth.common.OAuthType;
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
