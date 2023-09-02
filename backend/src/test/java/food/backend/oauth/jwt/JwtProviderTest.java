package food.backend.oauth.jwt;

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
        String jwt = jwtProvider.createJwt("accessToken");
        Assertions.assertNotNull(jwt);
    }

    @Test
    public void getMemberIdFromJwt() {
        String memberId = "123412";
        String jwt = jwtProvider.createJwt(memberId);

        String memberIdFromJwt = jwtProvider.getMemberIdFromJwt(jwt);

        Assertions.assertTrue(memberId.equals(memberIdFromJwt));
    }
}
