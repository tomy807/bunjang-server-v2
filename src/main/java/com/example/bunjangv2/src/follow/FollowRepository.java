package com.example.bunjangv2.src.follow;

import com.example.bunjangv2.entity.Follow;
import com.example.bunjangv2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow,Long> {

    List<Follow> findByFromUser(User user);
    List<Follow> findByToUser(User user);
}
