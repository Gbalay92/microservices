package com.ecommerce.userservice.domain.port.in;

import com.ecommerce.userservice.domain.model.User;

import java.util.UUID;

public interface GetUserProfile {

    User getProfile(UUID id);
}