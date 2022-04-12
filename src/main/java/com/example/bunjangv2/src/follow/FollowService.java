package com.example.bunjangv2.src.follow;

import com.example.bunjangv2.entity.Follow;
import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.src.follow.dto.FollowDto;
import com.example.bunjangv2.src.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public void followUser(Long toUserId, String name) {

        User fromUser = userRepository.findByEmail(name).orElseThrow(() -> new EntityNotFoundException("유저를 찾을수 없습니다."));
        User toUser = userRepository.findById(toUserId).orElseThrow(() -> new EntityNotFoundException("팔로우할 유저를 찾을수 없습니다."));
        followRepository.save(new Follow(fromUser, toUser));
    }

    public List<FollowDto> getFollowings(Long userIdx) {

        User user = userRepository.findById(userIdx).get();
//        List<Follow> followings = followRepository.findByFromUser(user);
        List<Follow> followings = user.getFollowings();
        List<FollowDto> followingList = new ArrayList<>();
        for (Follow following : followings) {
            User toUser = following.getToUser();
            int followerCount = followRepository.findByToUser(toUser).size();
            followingList.add(new FollowDto(toUser, followerCount, toUser.getProducts()));
        }
        return followingList;
    }

    public List<FollowDto> getFollowers(Long userIdx) {

        User user = userRepository.findById(userIdx).get();
//        List<Follow> followings = followRepository.findByFromUser(user);
        List<Follow> followers = user.getFollowers();
        List<FollowDto> followerList = new ArrayList<>();
        for (Follow follower : followers) {
            User fromUser = follower.getFromUser();
            int followerCount = followRepository.findByToUser(fromUser).size();
            followerList.add(new FollowDto(fromUser, followerCount, fromUser.getProducts()));
        }
        return followerList;
    }
}
