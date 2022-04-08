package com.example.bunjangv2.src.category.dto;

import com.example.bunjangv2.entity.CategoryLarge;
import com.example.bunjangv2.entity.CategoryMiddle;
import com.example.bunjangv2.entity.CategorySmall;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CategoryTotal {

    private CategoryLarge categoryLarge;

    private CategoryMiddle categoryMiddle;

    private CategorySmall categorySmall;
}
