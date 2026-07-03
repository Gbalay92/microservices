package com.ecommerce.orderservice.domain.port.in;

import com.ecommerce.orderservice.domain.model.Order;

import java.util.List;
import java.util.UUID;

public interface GetMyOrders {

    List<Order> execute(UUID userId);
}
