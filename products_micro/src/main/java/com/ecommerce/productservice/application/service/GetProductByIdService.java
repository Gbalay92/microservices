package com.ecommerce.productservice.application.service;

import com.ecommerce.productservice.domain.model.Product;
import com.ecommerce.productservice.domain.port.in.GetProductById;
import com.ecommerce.productservice.domain.port.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetProductByIdService implements GetProductById {
    private final ProductRepository productRepository;

    @Override
    public Product getById(String id) {
        return productRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }
}
