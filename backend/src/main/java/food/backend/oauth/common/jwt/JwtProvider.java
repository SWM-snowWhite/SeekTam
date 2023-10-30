package food.backend.oauth.common.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwtSecretKey;

    public String createJwt(String subject, int expirationTime) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expirationTime * 60 * 24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Key getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));
    }

    public String getEmailFromJwt(String jwt) {
        return Jwts.parserBuilder().setSigningKey(getKey()).build()
                .parseClaimsJws(jwt).getBody().getSubject();
    }

    public boolean validateJwt(String jwt) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parse(jwt);
            return true;
        } catch (Exception e) {
            log.error("{}", e);
            return false;
        }
    }

}
