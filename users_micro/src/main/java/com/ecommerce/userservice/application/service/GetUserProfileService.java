package com.ecommerce.userservice.application.service;

import com.ecommerce.userservice.domain.model.User;
import com.ecommerce.userservice.domain.port.in.GetUserProfile;
import com.ecommerce.userservice.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetUserProfileService implements GetUserProfile {

    private final UserRepository userRepository;

    @Override
    public User getProfile(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}