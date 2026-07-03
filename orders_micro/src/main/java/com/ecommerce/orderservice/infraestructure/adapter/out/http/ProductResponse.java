package com.ecommerce.orderservice.infraestructure.adapter.out.http;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(UUID id, String name, BigDecimal price) {
}
