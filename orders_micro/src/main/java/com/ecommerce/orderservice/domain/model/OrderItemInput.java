package com.ecommerce.orderservice.domain.model;

import java.util.UUID;

public record OrderItemInput( UUID productId, int quantity) {
}
