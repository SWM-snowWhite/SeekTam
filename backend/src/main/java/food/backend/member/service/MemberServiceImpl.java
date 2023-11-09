package food.backend.member.service;

import food.backend.member.Member;
import food.backend.member.MemberRepository;
import food.backend.member.dao.LikeDao;
import food.backend.member.dto.SignupRequestDto;
import food.backend.member.request.LikeRequest;
import food.backend.member.request.MemberDto;
import food.backend.member.response.LikeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final LikeDao likeDao;
    public Member signup(SignupRequestDto signupRequestDto) {
        Member savedMember = memberRepository.save(signupRequestDto.toEntity());
        log.info("savedMember" + savedMember.toString());
        return savedMember;
    }

    public Member signup(Member member) {
        Member savedMember = memberRepository.save(member);
        log.info("savedMember" + savedMember.toString());
        return savedMember;
    }


    public Optional<Member> findById(Long id) {
        Optional<Member> savedMember = memberRepository.findById(id);
        log.info("savedMember" + savedMember.toString());
        return savedMember;
    }

    public void pushLikeFood(LikeRequest likeRequest, MemberDto memberDto) {
        likeDao.pushLikeFood(likeRequest, memberDto.getEmail());
    }

    public void unlikeFood(LikeRequest likeRequest, MemberDto memberDto) {
        likeDao.unlikeFood(likeRequest, memberDto.getEmail());
    }

    public List<LikeResponse> getLikeList(MemberDto memberDto) {
        return likeDao.getLikeList(memberDto.getEmail());
    }

    public boolean checkLike(LikeRequest likeRequest, MemberDto memberDto) {
        return likeDao.checkLike(memberDto.getEmail(), likeRequest.getFoodId());
    }

    public boolean logout(MemberDto memberDto, HttpServletRequest request, HttpServletResponse response) {
        Optional<Member> member = memberRepository.findByEmail(memberDto.getEmail());

        if (member.isPresent()) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
