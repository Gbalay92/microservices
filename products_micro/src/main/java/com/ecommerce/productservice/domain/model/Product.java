package com.ecommerce.productservice.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class Product {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private Integer stock;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}