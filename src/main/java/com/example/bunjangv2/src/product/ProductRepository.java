package com.example.bunjangv2.src.product;

import com.example.bunjangv2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
