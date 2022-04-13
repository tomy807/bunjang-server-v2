package com.example.bunjangv2.src.product;

import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.src.product.dto.ProductDto;
import com.example.bunjangv2.src.product.dto.ProductPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("")
    public ResponseEntity postProduct(@Valid @RequestBody ProductPostDto productDto) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        productService.createProduct(productDto, user.getId());

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
