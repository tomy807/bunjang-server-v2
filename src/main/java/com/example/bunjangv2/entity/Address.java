package com.example.bunjangv2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 10, columnDefinition = "varchar(10) DEFAULT 'SUB'")
    private String main;

    @Column(nullable = false,length = 100)
    private String address;

    @Column(length = 100)
    private String detailAddress;






}
