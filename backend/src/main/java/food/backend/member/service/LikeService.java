package food.backend.member.service;

import food.backend.member.dao.LikeDao;
import food.backend.member.request.LikeRequest;
import food.backend.member.response.LikeResponse;
import food.backend.oauth.common.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeDao likeDao;
    private final JwtProvider jwtProvider;
    public void pushLikeFood(LikeRequest likeRequest, HttpServletRequest request) {
        String jwt = getTokenFromCookie(request, "access_token");
        String memberId = jwtProvider.getMemberIdFromJwt(jwt);

        likeDao.pushLikeFood(likeRequest, memberId);
    }

    public void unlikeFood(LikeRequest likeRequest, HttpServletRequest request) {
        String jwt = getTokenFromCookie(request, "access_token");
        String memberId = jwtProvider.getMemberIdFromJwt(jwt);

        likeDao.unlikeFood(likeRequest, memberId);
    }

    public List<LikeResponse> getLikeList(HttpServletRequest request) {
        String jwt = getTokenFromCookie(request, "access_token");
        String memberId = jwtProvider.getMemberIdFromJwt(jwt);

        return likeDao.getLikeList(memberId);
    }



    private String getTokenFromCookie(HttpServletRequest request, String tokenName) {
        Cookie[] cookies = request.getCookies();

        return Arrays.stream(cookies)
                .filter(cookie -> tokenName.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }
}
