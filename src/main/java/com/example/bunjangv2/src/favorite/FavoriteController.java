package com.example.bunjangv2.src.favorite;


import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.src.favorite.dto.FavoriteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping("")
    public ResponseEntity getFavorites(@AuthenticationPrincipal User user) {
        List<FavoriteDto> favorites = favoriteService.getFavorites(user);
        return ResponseEntity.ok(favorites);
    }

    @PostMapping("/{productIdx}")
    public ResponseEntity exchangeFavorite(@PathVariable Long productIdx, @AuthenticationPrincipal User user) {

        String result = favoriteService.exchangeFavorite(productIdx, user);

        return ResponseEntity.ok(result);
    }
}
