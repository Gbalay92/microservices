package com.ecommerce.productservice.infrastructure.adapter.out.db;

import com.ecommerce.productservice.domain.model.Product;
import com.ecommerce.productservice.domain.port.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductJpaAdapter implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    @Override
    public List<Product> findAll(String category) {
        if (category != null && !category.isBlank()) {
            return jpaProductRepository.findByCategory(category).stream()
                    .map(this::toDomain)
                    .toList();
        }
        return jpaProductRepository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return jpaProductRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .stock(product.getStock())
                .active(product.getActive() != null ? product.getActive() : true)
                .createdAt(product.getCreatedAt() != null ? product.getCreatedAt() : LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return toDomain(jpaProductRepository.save(entity));
    }

    @Override
    public void deleteById(UUID id) {
        jpaProductRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return jpaProductRepository.existsById(id);
    }

    private Product toDomain(ProductEntity entity) {
        return Product.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .category(entity.getCategory())
                .stock(entity.getStock())
                .active(entity.getActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}