package com.ecommerce.orderservice.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class Order {
    private OrderStatus status;
    private UUID id;
    private UUID userId;
    private List<OrderItem> items;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
}
