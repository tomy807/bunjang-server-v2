package com.example.bunjangv2.src;

import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.src.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final UserRepository userRepository;

    @GetMapping("/test")
    public ResponseEntity getTest() {

        User byIdx = userRepository.findByIdx(1L);
        return ResponseEntity.ok(byIdx);
    }

}
