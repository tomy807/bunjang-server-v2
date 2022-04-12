package com.example.bunjangv2.src.follow;


import com.example.bunjangv2.src.follow.dto.FollowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow/{toUserId}")
    public ResponseEntity followUser(@PathVariable Long toUserId, Authentication authentication) {
        followService.followUser(toUserId, authentication.getName());
        return ResponseEntity.ok("success");
    }

    @GetMapping("/following/{userIdx}")
    public ResponseEntity<List<FollowDto>> getFollowings(@PathVariable Long userIdx) {

        List<FollowDto> followings = followService.getFollowings(userIdx);

        return ResponseEntity.ok(followings);
    }

    @GetMapping("/follower/{userIdx}")
    public ResponseEntity<List<FollowDto>> getFollowers(@PathVariable Long userIdx) {

        List<FollowDto> followers = followService.getFollowers(userIdx);

        return ResponseEntity.ok(followers);
    }
}
