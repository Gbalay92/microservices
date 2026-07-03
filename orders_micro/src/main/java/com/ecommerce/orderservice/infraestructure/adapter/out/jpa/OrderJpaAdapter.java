package com.ecommerce.orderservice.infraestructure.adapter.out.jpa;

import com.ecommerce.orderservice.domain.model.Order;
import com.ecommerce.orderservice.domain.model.OrderItem;
import com.ecommerce.orderservice.domain.port.out.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderJpaAdapter implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    @Override
    public Order save(Order order) {
        OrderEntity entity = toEntity(order);
        return toDomain(jpaOrderRepository.save(entity));
    }

    @Override
    public Optional<Order> findById(UUID orderId) {
        return jpaOrderRepository.findById(orderId).map(this::toDomain);
    }

    @Override
    public List<Order> findByUserId(UUID userId) {
        return jpaOrderRepository.findByUserId(userId).stream()
                .map(this::toDomain)
                .toList();
    }

    private Order toDomain(OrderEntity entity) {
        List<OrderItem> items = entity.getItems().stream().map(item ->
                OrderItem.builder()
                        .id(item.getId())
                        .productId(item.getProductId())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .build()
        ).toList();

        return Order.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .items(items)
                .totalPrice(entity.getTotalPrice())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    private OrderEntity toEntity(Order order) {
        OrderEntity entity = OrderEntity.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();

        List<OrderItemEntity> items = order.getItems().stream().map(item ->
                OrderItemEntity.builder()
                        .id(item.getId())
                        .productId(item.getProductId())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .order(entity)
                        .build()
        ).toList();

        entity.setItems(items);
        return entity;
    }

}
