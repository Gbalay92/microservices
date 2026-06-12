package com.ecommerce.userservice.domain.port.in;

import com.ecommerce.userservice.domain.model.User;

import java.util.UUID;

public interface UpdateUserProfile {

    User update(UUID id, String name, String lastname);
}