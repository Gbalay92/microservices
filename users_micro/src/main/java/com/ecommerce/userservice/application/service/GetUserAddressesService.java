package com.ecommerce.userservice.application.service;

import com.ecommerce.userservice.domain.model.Address;
import com.ecommerce.userservice.domain.port.in.GetUserAddresses;
import com.ecommerce.userservice.domain.port.out.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetUserAddressesService implements GetUserAddresses {

    private final AddressRepository addressRepository;

    @Override
    public List<Address> getAddresses(UUID userId) {
        return addressRepository.findByUserId(userId);
    }
}