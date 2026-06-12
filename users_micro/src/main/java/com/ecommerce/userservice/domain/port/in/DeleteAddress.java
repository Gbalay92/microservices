package com.ecommerce.userservice.domain.port.in;

import java.util.UUID;

public interface DeleteAddress {

    void delete(UUID id);
}