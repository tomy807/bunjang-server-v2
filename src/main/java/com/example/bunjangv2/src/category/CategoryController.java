package com.example.bunjangv2.src.category;

import com.example.bunjangv2.entity.CategoryLarge;
import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.src.category.dto.CategoryDto;
import com.example.bunjangv2.src.category.dto.CategoryTotal;
import com.example.bunjangv2.src.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryTotal category = categoryService.createCategory(categoryDto);

        return ResponseEntity.ok(category);

    }

}
