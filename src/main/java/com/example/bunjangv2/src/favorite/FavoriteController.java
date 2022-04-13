package com.example.bunjangv2.src.favorite;


import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.src.favorite.dto.FavoriteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping("")
    public ResponseEntity getFavorite() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        List<FavoriteDto> favorites = favoriteService.getFavorites(user);
        return ResponseEntity.ok(favorites);
    }

    @PostMapping("/{productIdx}")
    public ResponseEntity exchangeFavorite(@PathVariable Long productIdx) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        String result = favoriteService.exchangeFavorite(productIdx, user);

        return ResponseEntity.ok(result.getBytes(StandardCharsets.UTF_8));
    }

}
