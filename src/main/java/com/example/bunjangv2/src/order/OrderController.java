package com.example.bunjangv2.src.order;

import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.src.order.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("")
    public ResponseEntity createOrder(@RequestBody OrderDto orderDto, @AuthenticationPrincipal User user) {
        orderService.createOrder(orderDto, user);
        return ResponseEntity.ok("success");
    }


}

