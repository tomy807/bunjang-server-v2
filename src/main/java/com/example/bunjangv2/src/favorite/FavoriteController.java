package com.example.bunjangv2.src.favorite;


import com.example.bunjangv2.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/favorite/{productIdx}")
    public ResponseEntity exchangeFavorite(@PathVariable Long productIdx, @AuthenticationPrincipal User user) {

        String result = favoriteService.exchangeFavorite(productIdx, user);

        return ResponseEntity.ok(result);
    }
}
