package com.example.bunjangv2.src.user;

import com.example.bunjangv2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query(value = "select u from User u where u.id=:userIdx")
    User findByIdx(Long userIdx);

}
