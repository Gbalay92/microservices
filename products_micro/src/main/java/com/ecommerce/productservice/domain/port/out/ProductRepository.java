package com.ecommerce.productservice.domain.port.out;

import com.ecommerce.productservice.domain.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    List<Product> findAll(String category);
    Optional<Product> findById(UUID id);
    Product save(Product product);
    void deleteById(UUID id);
    boolean existsById(UUID id);
}
