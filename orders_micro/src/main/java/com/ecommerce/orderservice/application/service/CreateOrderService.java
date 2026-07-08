package com.ecommerce.orderservice.application.service;

import com.ecommerce.orderservice.domain.model.*;
import com.ecommerce.orderservice.domain.port.in.CreateOrder;
import com.ecommerce.orderservice.domain.port.out.OrderRepository;
import com.ecommerce.orderservice.domain.port.out.ProductClient;
import com.ecommerce.orderservice.infraestructure.adapter.in.web.OrderItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CreateOrderService implements CreateOrder {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    @Override
    public Order execute(UUID userId, List<OrderItemRequest> items) {
        List<OrderItem> orderItems = items.stream().map(req -> {
            ProductInfo product = productClient.findById(req.productId());
            return OrderItem.builder()
                    .productId(req.productId())
                    .quantity(req.quantity())
                    .unitPrice(product.price())
                    .build();
        }).toList();

        BigDecimal totalPrice = orderItems.stream()
                .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return orderRepository.save(Order.builder()
                .userId(userId)
                .items(orderItems)
                .totalPrice(totalPrice)
                .status(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build());
    }
}