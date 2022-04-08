package com.example.bunjangv2.src.address;

import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.src.address.dto.DeliveryAddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/delivery")
    public ResponseEntity createDeliveryAddress(@Valid @RequestBody DeliveryAddressDto deliveryAddressDto, @AuthenticationPrincipal User user) {

        addressService.createDeliveryAddress(deliveryAddressDto, user);

        return ResponseEntity.ok("success");
    }

    @PostMapping("/direct")
    public ResponseEntity createDirectAddress(@Valid @RequestBody DeliveryAddressDto deliveryAddressDto, @AuthenticationPrincipal User user) {

        addressService.createDirectAddress(deliveryAddressDto, user);

        return ResponseEntity.ok("success");
    }
}
