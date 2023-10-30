package food.backend.member.service;

import food.backend.member.Member;
import food.backend.member.MemberRepository;
import food.backend.member.dto.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
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
}
