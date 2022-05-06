package com.example.bunjangv2.entity;

import com.example.bunjangv2.src.address.dto.DeliveryAddressDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "address")
@DynamicInsert
public class Address extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 10, columnDefinition = "varchar(10) DEFAULT 'SUB'")
    private String main;

    @Column(nullable = false,length = 100)
    private String address;

    @Column(length = 100)
    private String detailAddress;

    @Column(length = 10, columnDefinition = "varchar(10) default 'DELIVERY'")
    private String addressType;

    public Address(User user, DeliveryAddressDto deliveryAddressDto,String addressType) {

        this.user = user;
        this.main = deliveryAddressDto.getMain();
        this.address = deliveryAddressDto.getAddress();
        this.detailAddress = deliveryAddressDto.getDetailAddress();
        this.addressType = addressType;
    }

    public void setUser(User user) {
        this.user = user;
        user.getAddresses().add(this);
    }
}
