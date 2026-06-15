package com.ecommerce.userservice.application.service;

import com.ecommerce.userservice.domain.model.User;
import com.ecommerce.userservice.domain.port.in.AuthenticateUser;
import com.ecommerce.userservice.domain.port.out.UserRepository;
import com.ecommerce.userservice.infrastructure.adapter.out.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateUserService implements AuthenticateUser {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return jwtService.generateToken(user.getId(), user.getEmail(), user.getRole().name());
    }
}