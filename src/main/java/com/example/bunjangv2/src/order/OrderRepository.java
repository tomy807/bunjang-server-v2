package com.example.bunjangv2.src.order;


import com.example.bunjangv2.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
