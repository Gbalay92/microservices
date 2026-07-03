package com.ecommerce.orderservice.domain.model;

import java.util.UUID;

public record OrderItemRequest(UUID productId, int quantity) {
}