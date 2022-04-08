package com.example.bunjangv2.src.product;

import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.src.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("")
    public ResponseEntity postProduct(@Valid @RequestBody ProductDto productDto, @AuthenticationPrincipal User user) {

        productService.createProduct(productDto, user);

        return ResponseEntity.ok("success");

    }

    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> products = productService.getProducts();

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productIdx}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productIdx) {

        ProductDto productDto = productService.getProduct(productIdx);

        return ResponseEntity.ok(productDto);
    }
}
