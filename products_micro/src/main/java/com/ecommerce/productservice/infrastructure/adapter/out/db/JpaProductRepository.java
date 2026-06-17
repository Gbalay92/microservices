package com.ecommerce.productservice.infrastructure.adapter.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaProductRepository extends JpaRepository<ProductEntity, UUID> {
    List<ProductEntity> findByCategory(String category);
}
