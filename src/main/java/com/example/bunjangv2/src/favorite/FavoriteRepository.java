package com.example.bunjangv2.src.favorite;


import com.example.bunjangv2.entity.Favorite;
import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.src.favorite.dto.FavoriteDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUser(User user);

    @Query(value = "select new com.example.bunjangv2.src.favorite.dto.FavoriteDto(p.id,p.title,p.price,p.sellStatus,u.shopName,p.securePayment,p.createdDate) " +
            "from Favorite f " +
            "join f.product p " +
            "join p.user u " +
            "where f.user=:#{#user}")
    List<FavoriteDto> findFavorites(@Param("user") User user);

}
