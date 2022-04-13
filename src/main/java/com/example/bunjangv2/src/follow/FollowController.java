package com.example.bunjangv2.src.follow;


import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.src.follow.dto.FollowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow/{toUserId}")
    public ResponseEntity followUser(@PathVariable Long toUserId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        followService.followUser(toUserId, user);

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
