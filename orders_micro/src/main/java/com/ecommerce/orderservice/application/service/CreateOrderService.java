package com.ecommerce.orderservice.application.service;

import com.ecommerce.orderservice.domain.model.*;
import com.ecommerce.orderservice.domain.port.in.CreateOrder;
import com.ecommerce.orderservice.domain.port.out.OrderRepository;
import com.ecommerce.orderservice.domain.port.out.ProductClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(CreateOrderService.class);

    @Override
    public Order execute(UUID userId, List<OrderItemInput> items) {
        log.info("Creating order for user {}", userId);

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