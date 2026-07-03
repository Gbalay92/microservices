package com.ecommerce.orderservice.domain.port.in;

import com.ecommerce.orderservice.domain.model.Order;

import java.util.Optional;
import java.util.UUID;

public interface GetOrderById {
    Order execute(UUID orderId, UUID userId);
}
