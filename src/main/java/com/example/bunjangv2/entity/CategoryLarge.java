package com.example.bunjangv2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CategoryLarge extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "category_large_id")
    private Long id;

    @Column(nullable = false, length = 40)
    private String categoryLargeName;


}
