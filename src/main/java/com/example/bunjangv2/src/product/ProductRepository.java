package com.example.bunjangv2.src.product;

import com.example.bunjangv2.entity.Product;
import com.example.bunjangv2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByUserAndSellStatus(User user, String sellStatus);
}
