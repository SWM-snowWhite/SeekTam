package food.backend.member.service;

import food.backend.member.dao.LikeDao;
import food.backend.member.request.LikeRequest;
import food.backend.member.request.MemberDto;
import food.backend.member.response.LikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeDao likeDao;

    public void pushLikeFood(LikeRequest likeRequest, MemberDto memberDto) {
        likeDao.pushLikeFood(likeRequest, memberDto.getMemberId());
    }

    public void unlikeFood(LikeRequest likeRequest, MemberDto memberDto) {
        likeDao.unlikeFood(likeRequest, memberDto.getMemberId());
    }

    public List<LikeResponse> getLikeList(MemberDto memberDto) {
        return likeDao.getLikeList(memberDto.getMemberId());
    }

    public boolean checkLike(LikeRequest likeRequest, MemberDto memberDto) {
        return likeDao.checkLike(memberDto.getMemberId(), likeRequest.getFoodId());
    }


}
