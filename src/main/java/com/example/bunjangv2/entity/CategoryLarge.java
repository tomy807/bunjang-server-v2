package com.example.bunjangv2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@Table(name = "category_large")
public class CategoryLarge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_large_id")
    private Long id;

    @Column(nullable = false, length = 40)
    private String categoryLargeName;


}
