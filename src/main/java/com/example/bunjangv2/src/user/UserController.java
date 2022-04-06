package com.example.bunjangv2.src.user;


import com.example.bunjangv2.config.BaseResponse;
import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.src.user.dto.LoginDto;
import com.example.bunjangv2.src.user.dto.LoginResDto;
import com.example.bunjangv2.src.user.dto.SignDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity join(@Valid @RequestBody SignDto signDto) {
        userService.join(signDto);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>("회원가입이 되었습니다."));
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginDto loginDto) {
        LoginResDto login = userService.login(loginDto);

        return ResponseEntity.ok(login);
    }


}
