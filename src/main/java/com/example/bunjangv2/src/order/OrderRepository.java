package com.example.bunjangv2.src.order;


import com.example.bunjangv2.entity.Order;
import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.src.user.dto.MyPageDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);

    List<Order> findByProduct_User(User user);

//    @Query(value = "select new com.example.bunjangv2.src.user.dto.MyPageDto.MyPurchaseOrder(o.id,o.orderStatus,p.title,p.price,u.shopName,p.securePayment,o.createdDate) " +
//            "from Orders o " +
//            "join o.product p " +
//            "join p.user u " +
//            "where o.user.id=:#{#userIdx}")
//    List<MyPageDto.MyPurchaseOrder> findMyPurchaseList(@Param("userIdx") Long userIdx);
}
