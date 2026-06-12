package com.ecommerce.userservice.application.service;

import com.ecommerce.userservice.domain.port.in.DeleteAddress;
import com.ecommerce.userservice.domain.port.out.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteAddressService implements DeleteAddress {

    private final AddressRepository addressRepository;

    @Override
    public void delete(UUID id) {
        addressRepository.delete(id);
    }
}