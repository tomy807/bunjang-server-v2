package com.example.bunjangv2.src.favorite;


import com.example.bunjangv2.entity.Favorite;
import com.example.bunjangv2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUser(User user);
}
