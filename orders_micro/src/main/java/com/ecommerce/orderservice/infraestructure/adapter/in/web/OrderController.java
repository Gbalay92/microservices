package com.ecommerce.orderservice.infraestructure.adapter.in.web;


import com.ecommerce.orderservice.domain.model.OrderItemInput;
import com.ecommerce.orderservice.domain.port.in.CreateOrder;
import com.ecommerce.orderservice.domain.port.in.GetMyOrders;
import com.ecommerce.orderservice.domain.port.in.GetOrderById;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrder createOrder;
    private final GetMyOrders getMyOrders;
    private final GetOrderById getOrderById;

    @GetMapping
    public List<OrderResponse> getMyOrders() {
        return getMyOrders.execute(getUserId()).stream().map(OrderResponse::from).toList();
    }


    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable UUID id){
        return OrderResponse.from(getOrderById.execute(id, getUserId()));
    }

    @PostMapping
    public OrderResponse createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return OrderResponse.from(createOrder.execute(getUserId(), orderRequest.items().stream().map(
                i -> new OrderItemInput(i.productId(), i.quantity())
        ).toList()));
    }


    @Nonnull
    private static UUID getUserId() {
        return UUID.fromString((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
