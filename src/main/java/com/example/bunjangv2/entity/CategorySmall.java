package com.example.bunjangv2.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CategorySmall extends BaseTimeEntity {


    @Id
    @GeneratedValue
    @Column(name = "category_small_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_large_id")
    private CategoryMiddle categoryMiddle;

    @Column(nullable = false, length = 40)
    private String categorySmallName;


}
