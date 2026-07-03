package com.ecommerce.orderservice.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
public class OrderItem {
    private UUID id;
    private UUID productId;
    private int quantity;
    private BigDecimal unitPrice;
}
