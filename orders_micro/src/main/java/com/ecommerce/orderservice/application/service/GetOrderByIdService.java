package com.ecommerce.orderservice.application.service;

import com.ecommerce.orderservice.domain.exception.AccessDeniedException;
import com.ecommerce.orderservice.domain.exception.OrderNotFoundException;
import com.ecommerce.orderservice.domain.model.Order;
import com.ecommerce.orderservice.domain.port.in.GetOrderById;
import com.ecommerce.orderservice.domain.port.out.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetOrderByIdService implements GetOrderById {

    private final OrderRepository orderRepository;

    @Override
    public Order execute(UUID orderId, UUID userId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId.toString()));

        if (!order.getUserId().equals(userId)) {
            throw new AccessDeniedException();
        }

        return order;
    }
}
