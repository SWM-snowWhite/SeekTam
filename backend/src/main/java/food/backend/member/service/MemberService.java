package food.backend.member.service;

import food.backend.member.Member;
import food.backend.member.dto.SignupRequestDto;

import java.util.Optional;

public interface MemberService {
    Member signup(SignupRequestDto signupRequestDto);

    Member signup(Member member);

    Optional<Member> findById(Long id);
}
