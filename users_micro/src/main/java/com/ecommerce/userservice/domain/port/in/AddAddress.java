package com.ecommerce.userservice.domain.port.in;

import com.ecommerce.userservice.domain.model.Address;

import java.util.UUID;

public interface AddAddress {

    Address add(UUID userId, String street, String city, String country, String zipCode, boolean isDefault);
}