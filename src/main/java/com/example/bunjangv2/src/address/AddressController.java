package com.example.bunjangv2.src.address;

import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.src.address.dto.DeliveryAddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity createDeliveryAddress(@Valid @RequestBody DeliveryAddressDto deliveryAddressDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        addressService.createDeliveryAddress(deliveryAddressDto, user);

        return ResponseEntity.ok("배달지를 저장했습니다.");
    }

    @PostMapping("/direct")
    public ResponseEntity createDirectAddress(@Valid @RequestBody DeliveryAddressDto deliveryAddressDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        addressService.createDirectAddress(deliveryAddressDto, user);

        return ResponseEntity.ok("직거래장소를 저장했습니다.");
    }
}
