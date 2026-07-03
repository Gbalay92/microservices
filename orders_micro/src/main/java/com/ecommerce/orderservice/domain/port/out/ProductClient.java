package com.ecommerce.orderservice.domain.port.out;

import com.ecommerce.orderservice.domain.model.ProductInfo;

import java.util.UUID;

public interface ProductClient {
    ProductInfo findById(UUID productId);
}
