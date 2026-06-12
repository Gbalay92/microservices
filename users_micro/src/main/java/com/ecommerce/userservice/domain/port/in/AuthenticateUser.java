package com.ecommerce.userservice.domain.port.in;

public interface AuthenticateUser {

    String authenticate(String email, String password);
}