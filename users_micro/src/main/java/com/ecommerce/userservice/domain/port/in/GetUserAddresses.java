package com.ecommerce.userservice.domain.port.in;

import com.ecommerce.userservice.domain.model.Address;

import java.util.List;
import java.util.UUID;

public interface GetUserAddresses {

    List<Address> getAddresses(UUID userId);
}