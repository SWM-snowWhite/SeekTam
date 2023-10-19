package food.backend.member.service;

import food.backend.member.Member;
import food.backend.member.MemberRepository;
import food.backend.member.dto.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public Member signup(SignupRequestDto signupRequestDto) {
        Member savedMember = memberRepository.save(signupRequestDto.toEntity());
        return savedMember;
    }
}
