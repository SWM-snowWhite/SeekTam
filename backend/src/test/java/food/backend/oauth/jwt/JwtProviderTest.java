package food.backend.oauth.jwt;

import food.backend.oauth.common.jwt.JwtProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtProviderTest {

    @Autowired
    private JwtProvider jwtProvider;

    @Test
    public void CreateJwt() {
        String jwt = jwtProvider.createJwt("accessToken",11);
        Assertions.assertNotNull(jwt);
    }

    @Test
    public void getMemberIdFromJwt() {
        String memberId = "123412";
        String jwt = jwtProvider.createJwt(memberId, 11);

        String memberIdFromJwt = jwtProvider.getEmailFromJwt(jwt);

        Assertions.assertTrue(memberId.equals(memberIdFromJwt));
    }
}
