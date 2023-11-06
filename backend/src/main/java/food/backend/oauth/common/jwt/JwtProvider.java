package food.backend.oauth.common.jwt;

import java.security.Key;

public interface JwtProvider {
    public String createJwt(String subject, int expirationTime);

    public Key getKey();

    public String getEmailFromJwt(String jwt);

    public boolean validateJwt(String jwt);
}
