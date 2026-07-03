package com.ecommerce.orderservice.application.service;

import com.ecommerce.orderservice.domain.model.Order;
import com.ecommerce.orderservice.domain.port.in.GetMyOrders;
import com.ecommerce.orderservice.domain.port.out.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetMyOrdersService implements GetMyOrders {

    private final OrderRepository orderRepository;
    @Override
    public List<Order> execute(UUID userId) {
        return orderRepository.findByUserId(userId);
    }
}
