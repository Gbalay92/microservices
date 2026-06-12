package com.ecommerce.userservice.application.service;

import com.ecommerce.userservice.domain.model.Address;
import com.ecommerce.userservice.domain.port.in.AddAddress;
import com.ecommerce.userservice.domain.port.out.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddAddressService implements AddAddress {

    private final AddressRepository addressRepository;

    @Override
    public Address add(UUID userId, String street, String city, String country, String zipCode, boolean isDefault) {
        Address address = Address.builder()
                .userId(userId)
                .street(street)
                .city(city)
                .country(country)
                .zipCode(zipCode)
                .isDefault(isDefault)
                .build();

        return addressRepository.save(address);
    }
}