package com.example.bunjangv2.src.category;

import com.example.bunjangv2.entity.CategoryLarge;
import com.example.bunjangv2.entity.CategoryMiddle;
import com.example.bunjangv2.entity.CategorySmall;
import com.example.bunjangv2.exception.CategoryNotFoundException;
import com.example.bunjangv2.src.category.dto.CategoryDto;
import com.example.bunjangv2.src.category.dto.CategoryTotal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CategoryService {

    private final CategoryLargeRepository categoryLargeRepository;
    private final CategoryMiddleRepository categoryMiddleRepository;
    private final CategorySmallRepository categorySmallRepository;

    public CategoryTotal createCategory(CategoryDto categoryDto) {
        CategoryLarge categoryLarge = new CategoryLarge();
        CategoryMiddle categoryMiddle = new CategoryMiddle();
        CategorySmall categorySmall = new CategorySmall();


        categoryLarge.setCategoryLargeName(categoryDto.getCategoryLargeName());
        CategoryLarge savedCategoryLarge = categoryLargeRepository.save(categoryLarge);

        categoryMiddle.setCategoryMiddleName(categoryDto.getCategoryMiddleName());
        categoryMiddle.setCategoryLarge(savedCategoryLarge);
        CategoryMiddle savedCategoryMiddle = categoryMiddleRepository.save(categoryMiddle);

        categorySmall.setCategorySmallName(categoryDto.getCategorySmallName());
        categorySmall.setCategoryMiddle(savedCategoryMiddle);
        CategorySmall savedCategorySmall = categorySmallRepository.save(categorySmall);

        return new CategoryTotal(savedCategoryLarge, savedCategoryMiddle, savedCategorySmall);
    }

    public CategoryLarge findCategoryLarge(Long categoryLargeIdx) {
        return categoryLargeRepository.findById(categoryLargeIdx).orElseThrow(() -> new CategoryNotFoundException("categoryLarge"));
    }
    public CategoryMiddle findCategoryMiddle(Long categoryMiddleIdx) {
        return categoryMiddleRepository.findById(categoryMiddleIdx).orElseThrow(() -> new CategoryNotFoundException("categoryMiddle"));
    }
    public CategorySmall findCategorySmall(Long categorySmallIdx) {
        return categorySmallRepository.findById(categorySmallIdx).orElseThrow(() -> new CategoryNotFoundException("categorySmall"));
    }
}
