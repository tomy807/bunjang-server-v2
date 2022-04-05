package com.example.bunjangv2.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CategoryMiddle extends BaseTimeEntity {


    @Id
    @GeneratedValue
    @Column(name = "category_middle_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_large_id")
    private CategoryLarge categoryLarge;

    @Column(nullable = false, length = 40)
    private String categoryMiddleName;


}
