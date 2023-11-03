package food.backend.member.controller;

import food.backend.member.Member;
import food.backend.member.common.Authenticated;
import food.backend.member.dto.SignupRequestDto;
import food.backend.member.request.LikeRequest;
import food.backend.member.request.MemberDto;
import food.backend.member.response.LikeResponse;
import food.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PutMapping("/like")
    public ResponseEntity<Void> pushLikeFood(@RequestBody LikeRequest likeRequest, @Authenticated MemberDto memberDto) {
        memberService.pushLikeFood(likeRequest, memberDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/unlike")
    public ResponseEntity<Void> unlikeFood(@RequestBody LikeRequest likeRequest, @Authenticated MemberDto memberDto) {
        memberService.unlikeFood(likeRequest, memberDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/like-list")
    public List<LikeResponse> getLikeList(@Authenticated MemberDto memberDto) {
        return memberService.getLikeList(memberDto);
    }

    @GetMapping("/like-check")
    public boolean checkLike(@RequestBody LikeRequest likeRequest, @Authenticated MemberDto memberDto) {
        return memberService.checkLike(likeRequest, memberDto);
    }
    @PostMapping("/signup")
    public ResponseEntity<Member> signup(@RequestBody SignupRequestDto signupRequestDto) {
        return ResponseEntity.ok(memberService.signup(signupRequestDto));
    }
}
