package com.ecommerce.orderservice.application.service;

import com.ecommerce.orderservice.domain.exception.ProductNotFoundException;
import com.ecommerce.orderservice.domain.model.*;
import com.ecommerce.orderservice.domain.port.out.OrderRepository;
import com.ecommerce.orderservice.domain.port.out.ProductClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateOrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductClient productClient;

    @InjectMocks
    private CreateOrderService createOrderService;

    @Test
    void shouldCreateOrderWithCorrectTotalPrice() {
        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        when(productClient.findById(productId))
                .thenReturn(new ProductInfo(productId, new BigDecimal("100.00")));

        when(orderRepository.save(any(Order.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        List<OrderItemInput> items = List.of(new OrderItemInput(productId, 3));

        Order result = createOrderService.execute(userId, items);

        assertThat(result.getTotalPrice()).isEqualByComparingTo(new BigDecimal("300.00"));
        assertThat(result.getStatus()).isEqualTo(OrderStatus.PENDING);
        assertThat(result.getUserId()).isEqualTo(userId);
    }

    @Test
    void shouldCalculateTotalPriceForMultipleItems() {
        UUID userId = UUID.randomUUID();
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();

        when(productClient.findById(productId1))
                .thenReturn(new ProductInfo(productId1, new BigDecimal("50.00")));
        when(productClient.findById(productId2))
                .thenReturn(new ProductInfo(productId2, new BigDecimal("200.00")));

        when(orderRepository.save(any(Order.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        List<OrderItemInput> items = List.of(
                new OrderItemInput(productId1, 2),
                new OrderItemInput(productId2, 1)
        );

        Order result = createOrderService.execute(userId, items);

        assertThat(result.getTotalPrice()).isEqualByComparingTo(new BigDecimal("300.00"));
        assertThat(result.getItems()).hasSize(2);
    }

    @Test
    void shouldThrowProductNotFoundExceptionWhenProductDoesNotExist() {
        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        when(productClient.findById(productId))
                .thenThrow(new ProductNotFoundException(productId.toString()));

        List<OrderItemInput> items = List.of(new OrderItemInput(productId, 1));

        assertThatThrownBy(() -> createOrderService.execute(userId, items))
                .isInstanceOf(ProductNotFoundException.class);

        verify(orderRepository, never()).save(any());
    }
}