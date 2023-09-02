package food.backend.oauth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenInfo {
    private String accessToken;
    private String refreshToken;
}
