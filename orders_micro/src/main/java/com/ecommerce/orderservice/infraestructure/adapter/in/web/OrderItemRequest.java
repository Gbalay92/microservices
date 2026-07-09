package com.ecommerce.orderservice.infraestructure.adapter.in.web;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record OrderItemRequest(@NotNull UUID productId, @Positive int quantity) {
}