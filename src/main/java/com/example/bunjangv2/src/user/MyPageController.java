package com.example.bunjangv2.src.user;

import com.example.bunjangv2.config.BaseResponse;
import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.exception.ParameterException;
import com.example.bunjangv2.src.favorite.FavoriteController;
import com.example.bunjangv2.src.user.dto.MyPageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    private final UserService userService;

    @GetMapping("")
    public EntityModel<MyPageDto.UserInfo> getMyPage() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        MyPageDto.UserInfo myPage = userService.getMyPage(user);



//        ResponseEntity response = new ResponseEntity(HttpStatus.OK);
        EntityModel<MyPageDto.UserInfo> response = EntityModel.of(myPage);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(FavoriteController.class).getFavorite());
        response.add(linkTo.withRel("favorite"));
        return response;
//        return ResponseEntity.ok(new BaseResponse(myPage));
    }

    @GetMapping("/products")
    public ResponseEntity<BaseResponse> getMyProducts(@RequestParam(required = false) String status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        if (status == null || status.isEmpty()) {
            throw new ParameterException("status");
        }
        MyPageDto.MyProducts myProducts = userService.getMyProducts(user, status);

        return ResponseEntity.ok(new BaseResponse(myProducts));
    }

    @GetMapping("/followers")
    public ResponseEntity<BaseResponse> getMyFollowers() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        List<MyPageDto.MyFollower> myFollowers = userService.getMyFollowers(user);

        return ResponseEntity.ok(new BaseResponse(myFollowers));
    }

    @GetMapping("/followings")
    public ResponseEntity<BaseResponse> getMyFollowings() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        List<MyPageDto.MyFollowing> myFollowers = userService.getMyFollowings(user);

        return ResponseEntity.ok(new BaseResponse(myFollowers));
    }

    @GetMapping("/purchase")
    public ResponseEntity<BaseResponse> getPurchaseList() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        List<MyPageDto.MyPurchaseOrder> purchaseList = userService.getPurchaseList(user);

        return ResponseEntity.ok(new BaseResponse(purchaseList));
    }

    @GetMapping("/sell")
    public ResponseEntity getSellList(Authentication authentication) {

        List<MyPageDto.MySellOrder> sellList = userService.getSellList(authentication.getName());

        return ResponseEntity.ok(new BaseResponse<>(sellList));
    }
}
