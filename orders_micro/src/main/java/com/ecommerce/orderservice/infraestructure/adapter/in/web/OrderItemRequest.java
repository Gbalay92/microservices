package com.ecommerce.orderservice.infraestructure.adapter.in.web;

import java.util.UUID;

public record OrderItemRequest(UUID productId, int quantity) {
}