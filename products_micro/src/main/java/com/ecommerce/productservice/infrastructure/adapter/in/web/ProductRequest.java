package com.ecommerce.productservice.infrastructure.adapter.in.web;

import com.ecommerce.productservice.domain.model.Product;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        String description,
        BigDecimal price,
        String category,
        Integer stock
) {
    public Product toDomain() {
        return Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .category(category)
                .stock(stock)
                .build();
    }
}