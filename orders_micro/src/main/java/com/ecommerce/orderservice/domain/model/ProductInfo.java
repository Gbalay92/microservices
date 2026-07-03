package com.ecommerce.orderservice.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductInfo(UUID id, BigDecimal price) {
}
