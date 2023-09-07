package food.backend.member.controller;

import food.backend.member.request.LikeRequest;
import food.backend.member.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
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
}
