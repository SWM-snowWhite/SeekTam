package food.backend.member.controller;

import food.backend.member.request.LikeRequest;
import food.backend.member.response.LikeResponse;
import food.backend.member.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    // ArgumentResolver 활용 계획
    @PutMapping("/like")
    public ResponseEntity<Void> pushLikeFood(@RequestBody LikeRequest likeRequest, HttpServletRequest request) {
        likeService.pushLikeFood(likeRequest, request);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/unlike")
    public ResponseEntity<Void> unlikeFood(@RequestBody LikeRequest likeRequest, HttpServletRequest request) {
        likeService.unlikeFood(likeRequest, request);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/like-list")
    public List<LikeResponse> getLikeList(HttpServletRequest request) {
        return likeService.getLikeList(request);
    }

    @GetMapping("/like-check")
    public boolean checkLike(@RequestBody LikeRequest likeRequest, HttpServletRequest request) {
        return likeService.checkLike(likeRequest, request);
    }

}
