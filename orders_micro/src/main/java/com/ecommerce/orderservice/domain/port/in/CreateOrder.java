package com.ecommerce.orderservice.domain.port.in;

import com.ecommerce.orderservice.domain.model.Order;
import com.ecommerce.orderservice.domain.model.OrderItemInput;

import java.util.List;
import java.util.UUID;

public interface CreateOrder {
    Order execute(UUID userId, List<OrderItemInput> items);
}
