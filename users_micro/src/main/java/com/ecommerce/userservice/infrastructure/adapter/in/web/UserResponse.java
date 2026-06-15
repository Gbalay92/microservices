package com.ecommerce.userservice.infrastructure.adapter.in.web;

import com.ecommerce.userservice.domain.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(UUID id, String name, String lastname, String email,
                           LocalDateTime createdAt, LocalDateTime updatedAt) {
    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getLastname(),
                user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
