package com.ecommerce.userservice.application.service;

import com.ecommerce.userservice.domain.model.User;
import com.ecommerce.userservice.domain.port.in.UpdateUserProfile;
import com.ecommerce.userservice.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateUserProfileService implements UpdateUserProfile {

    private final UserRepository userRepository;

    @Override
    public User update(UUID id, String name, String lastname) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        User updated = User.builder()
                .id(user.getId())
                .name(name)
                .lastname(lastname)
                .email(user.getEmail())
                .password(user.getPassword())
                .createdAt(user.getCreatedAt())
                .build();

        return userRepository.save(updated);
    }
}