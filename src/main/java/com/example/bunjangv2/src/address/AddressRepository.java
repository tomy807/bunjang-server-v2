package com.example.bunjangv2.src.address;

import com.example.bunjangv2.entity.Address;
import com.example.bunjangv2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByMainAndAddressType(String main, String addressType);

    Long countAddressByAddressType(String addressType);

    Optional<Address> findAddressByUserAndMainAndAddressType(User user, String main, String addressType);

}
