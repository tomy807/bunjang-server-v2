package com.example.bunjangv2.src.user;

import com.example.bunjangv2.config.BaseResponse;
import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.exception.ParameterException;
import com.example.bunjangv2.src.user.dto.MyPageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<BaseResponse> getMyPage(@AuthenticationPrincipal User user) {

        MyPageDto.UserInfo myPage = userService.getMyPage(user);
        return ResponseEntity.ok(new BaseResponse(myPage));
    }

    @GetMapping("/products")
    public ResponseEntity<BaseResponse> getMyProducts(@AuthenticationPrincipal User user, @RequestParam(required = false) String status) {
        if (status == null || status.isEmpty()) {
            throw new ParameterException("status");
        }
        MyPageDto.MyProducts myProducts = userService.getMyProducts(user, status);

        return ResponseEntity.ok(new BaseResponse(myProducts));
    }

    @GetMapping("/followers")
    public ResponseEntity<BaseResponse> getMyFollowers(@AuthenticationPrincipal User user) {

        List<MyPageDto.MyFollower> myFollowers = userService.getMyFollowers(user);

        return ResponseEntity.ok(new BaseResponse(myFollowers));
    }

    @GetMapping("/followings")
    public ResponseEntity<BaseResponse> getMyFollowings(@AuthenticationPrincipal User user) {

        List<MyPageDto.MyFollowing> myFollowers = userService.getMyFollowings(user);

        return ResponseEntity.ok(new BaseResponse(myFollowers));
    }

    @GetMapping("/purchase")
    public ResponseEntity<BaseResponse> getPurchaseList(@AuthenticationPrincipal User user) {

        List<MyPageDto.MyPurchaseOrder> purchaseList = userService.getPurchaseList(user);

        return ResponseEntity.ok(new BaseResponse(purchaseList));
    }

    @GetMapping("/sell")
    public ResponseEntity getSellList(Authentication authentication) {

        List<MyPageDto.MySellOrder> sellList = userService.getSellList(authentication.getName());

        return ResponseEntity.ok(new BaseResponse<>(sellList));
    }
}
