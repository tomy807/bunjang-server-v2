package com.example.bunjangv2.src.shop;

import com.example.bunjangv2.src.shop.dto.ShopDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop/{shopIdx}")
public class ShopController {

    private final ShopService shopService;

    @GetMapping("")
    public ResponseEntity<ShopDto> getShop(@PathVariable Long shopIdx) {

        ShopDto shop = shopService.getShop(shopIdx);

        return ResponseEntity.ok(shop);

    }

    @GetMapping("/products")
    public ResponseEntity<List<ShopDto.ProductByShop>> getProducts(@PathVariable Long shopIdx) {
        List<ShopDto.ProductByShop> products = shopService.getProducts(shopIdx);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/followings")
    public ResponseEntity getFollowings(@PathVariable Long shopIdx) {
        List<ShopDto.Following> followings = shopService.getFollowings(shopIdx);
        return ResponseEntity.ok(followings);
    }
}
