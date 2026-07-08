package com.ecommerce.orderservice.infraestructure.adapter.in.web;

import com.ecommerce.orderservice.domain.model.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponse(UUID id, UUID userId, List<OrderItemResponse> items, BigDecimal totalPrice, String status, LocalDateTime createdAt) {
    public static OrderResponse from(Order order) {
        List<OrderItemResponse> items = order.getItems().stream()
                .map(i -> new OrderItemResponse(i.getProductId(), i.getQuantity(), i.getUnitPrice()))
                .toList();
        return new OrderResponse(order.getId(), order.getUserId(), items, order.getTotalPrice(), order.getStatus().name(), order.getCreatedAt());
    }
}