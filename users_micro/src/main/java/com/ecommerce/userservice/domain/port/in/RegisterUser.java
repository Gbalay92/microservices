package com.ecommerce.userservice.domain.port.in;

import com.ecommerce.userservice.domain.model.User;

public interface RegisterUser {

    User register(String name, String lastname, String email, String password);
}