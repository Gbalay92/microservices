package com.ecommerce.orderservice.application.service;

import com.ecommerce.orderservice.domain.exception.AccessDeniedException;
import com.ecommerce.orderservice.domain.exception.OrderNotFoundException;
import com.ecommerce.orderservice.domain.model.*;
import com.ecommerce.orderservice.domain.port.out.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetOrderByidServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private GetOrderByIdService getOrderByIdService;

    @Test
    void shouldReturnOrderWhenUserIsOwner() {
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        Order order = Order.builder()
                .id(orderId)
                .userId(userId)
                .items(List.of())
                .totalPrice(new BigDecimal("100.00"))
                .status(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Order result = getOrderByIdService.execute(orderId, userId);

        assertThat(result.getId()).isEqualTo(orderId);
        assertThat(result.getUserId()).isEqualTo(userId);
    }

    @Test
    void shouldThrowOrderNotFoundExceptionWhenOrderDoesNotExist() {
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> getOrderByIdService.execute(orderId, userId))
                .isInstanceOf(OrderNotFoundException.class);
    }

    @Test
    void shouldThrowAccessDeniedExceptionWhenUserIsNotOwner() {
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID otherUserId = UUID.randomUUID();

        Order order = Order.builder()
                .id(orderId)
                .userId(otherUserId)
                .items(List.of())
                .totalPrice(new BigDecimal("100.00"))
                .status(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        assertThatThrownBy(() -> getOrderByIdService.execute(orderId, userId))
                .isInstanceOf(AccessDeniedException.class);
    }
}