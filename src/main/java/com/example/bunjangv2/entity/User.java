package com.example.bunjangv2.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
@DynamicInsert
public class User extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 11)
    private String shopName;

    @Column(nullable = false, unique = true, length = 30)
    private String email;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(nullable = false, length = 11)
    private String phone;

    @Column(length = 10)
    private String provider;

    @Column(length = 200)
    private String profileUrl;

    @Column(columnDefinition = "integer DEFAULT 1000000")
    private Integer point;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @Column(length = 10, columnDefinition = "varchar(10) DEFAULT '남자'")
    private String gender;

    @Column(length = 20)
    private String birthDate;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Address> addresses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "fromUser", cascade = CascadeType.ALL)
    private List<Follow> followings;

    @OneToMany(mappedBy = "toUser", cascade = CascadeType.ALL)
    private List<Follow> followers;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
