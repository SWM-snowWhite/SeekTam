package food.backend.member.service;

import food.backend.member.Member;
import food.backend.member.dto.SignupRequestDto;
import food.backend.member.request.LikeRequest;
import food.backend.member.request.MemberDto;
import food.backend.member.response.LikeResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public interface MemberService {
    Member signup(SignupRequestDto signupRequestDto);

    Member signup(Member member);

    Optional<Member> findById(Long id);

    void pushLikeFood(LikeRequest likeRequest, MemberDto memberDto);
    void unlikeFood(LikeRequest likeRequest, MemberDto memberDto);

    List<LikeResponse> getLikeList(MemberDto memberDto);

    boolean checkLike(LikeRequest likeRequest, MemberDto memberDto);

    boolean logout(MemberDto memberDto, HttpServletRequest request, HttpServletResponse response);
}
