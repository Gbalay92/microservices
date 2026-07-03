package com.ecommerce.orderservice.infraestructure.adapter.out.jpa;

import com.ecommerce.orderservice.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaOrderRepository extends JpaRepository<OrderEntity, UUID>  {
    Optional<OrderEntity> findById(UUID orderId);
    OrderEntity save(OrderEntity orderEntity);
    Optional<OrderEntity> findByUserId(UUID userId);
}
