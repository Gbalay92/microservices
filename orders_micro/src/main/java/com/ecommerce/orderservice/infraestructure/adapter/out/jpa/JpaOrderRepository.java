package com.ecommerce.orderservice.infraestructure.adapter.out.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaOrderRepository extends JpaRepository<OrderEntity, UUID>  {
    Optional<OrderEntity> findById(UUID orderId);
    OrderEntity save(OrderEntity orderEntity);
    List<OrderEntity> findByUserId(UUID userId);
}
