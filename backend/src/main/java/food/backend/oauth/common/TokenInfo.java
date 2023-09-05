package food.backend.oauth.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class TokenInfo {
    private String accessToken;
    private String refreshToken;
}
