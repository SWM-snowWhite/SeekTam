package food.backend.member.controller;

import food.backend.member.common.Authenticated;
import food.backend.member.request.LikeRequest;
import food.backend.member.request.MemberDto;
import food.backend.member.response.LikeResponse;
import food.backend.member.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PutMapping("/like")
    public ResponseEntity<Void> pushLikeFood(@RequestBody LikeRequest likeRequest, @Authenticated MemberDto memberDto) {
        likeService.pushLikeFood(likeRequest, memberDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/unlike")
    public ResponseEntity<Void> unlikeFood(@RequestBody LikeRequest likeRequest, @Authenticated MemberDto memberDto) {
        likeService.unlikeFood(likeRequest, memberDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/like-list")
    public List<LikeResponse> getLikeList(@Authenticated MemberDto memberDto) {
        return likeService.getLikeList(memberDto);
    }

    @GetMapping("/like-check")
    public boolean checkLike(@RequestBody LikeRequest likeRequest, @Authenticated MemberDto memberDto) {
        return likeService.checkLike(likeRequest, memberDto);
    }

}
