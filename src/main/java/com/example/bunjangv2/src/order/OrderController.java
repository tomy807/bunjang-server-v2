package com.example.bunjangv2.src.order;

import com.example.bunjangv2.config.BaseResponse;
import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.src.order.dto.OrderDetailDto;
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
    public ResponseEntity<BaseResponse> createOrder(@RequestBody OrderDto orderDto, @AuthenticationPrincipal User user) {
        orderService.createOrder(orderDto, user);
        return ResponseEntity.ok().body(new BaseResponse<>("주문이 완료되었습니다."));
    }

    @PostMapping("/{orderIdx}/cancel")
    public ResponseEntity<BaseResponse> cancelOrder(@PathVariable Long orderIdx, @AuthenticationPrincipal User user) {
        orderService.cancelOrder(user, orderIdx);
        return ResponseEntity.ok().body(new BaseResponse<>("주문이 취소되었습니다."));
    }

    @GetMapping("/details/{orderIdx}")
    public ResponseEntity<BaseResponse<OrderDetailDto>> getOrderDetail(@PathVariable Long orderIdx, @AuthenticationPrincipal User user) {
        OrderDetailDto orderDetail = orderService.getOrderDetail(orderIdx, user);
        return ResponseEntity.ok().body(new BaseResponse<>(orderDetail));
    }

    @PostMapping("/{orderIdx}/confirm")
    public ResponseEntity<BaseResponse> confirmOrder(@PathVariable Long orderIdx, @AuthenticationPrincipal User user) {
        orderService.confirmOrder(orderIdx, user);
        return ResponseEntity.ok().body(new BaseResponse("주문이 확정되었습니다."));
    }
}

