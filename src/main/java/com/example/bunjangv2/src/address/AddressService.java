package com.example.bunjangv2.src.address;

import com.example.bunjangv2.entity.Address;
import com.example.bunjangv2.entity.User;
import com.example.bunjangv2.exception.LimitAddressCount;
import com.example.bunjangv2.src.address.dto.DeliveryAddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    @Transactional
    public void createDeliveryAddress(DeliveryAddressDto deliveryAddressDto, User user) {

        if (addressRepository.countAddressByAddressType("DELIVERY") == 3) {
            throw new LimitAddressCount();
        }

        Address address = new Address(user, deliveryAddressDto, "DELIVERY");
        Optional<Address> mainAddressOp = addressRepository.findByMainAndAddressType("MAIN", "DELIVERY");

        if (deliveryAddressDto.getMain().equals("MAIN") && mainAddressOp.isPresent()) {
            Address mainAddress = mainAddressOp.get();
            mainAddress.setMain("SUB");
            addressRepository.save(mainAddress);
        }
        else if (deliveryAddressDto.getMain().equals("SUB") && mainAddressOp.isEmpty()) {
            address.setMain("MAIN");
        }

        addressRepository.save(address);
    }

    @Transactional
    public void createDirectAddress(DeliveryAddressDto deliveryAddressDto, User user) {
        if (addressRepository.countAddressByAddressType("DIRECT") == 3) {
            throw new LimitAddressCount();
        }
        Address address = new Address(user, deliveryAddressDto, "DIRECT");
        Optional<Address> mainAddressOp = addressRepository.findByMainAndAddressType("MAIN", "DIRECT");

        if (deliveryAddressDto.getMain().equals("MAIN") && mainAddressOp.isPresent()) {
            Address mainAddress = mainAddressOp.get();
            mainAddress.setMain("SUB");
            addressRepository.save(mainAddress);
        }
        else if (deliveryAddressDto.getMain().equals("SUB") && mainAddressOp.isEmpty()) {
            address.setMain("MAIN");
        }

        addressRepository.save(address);

    }
}
