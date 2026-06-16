package com.ecommerce.productservice.application.service;

import com.ecommerce.productservice.domain.model.Product;
import com.ecommerce.productservice.domain.port.in.UpdateProduct;
import com.ecommerce.productservice.domain.port.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateProductService implements UpdateProduct {

    private final ProductRepository productRepository;

    @Override
    public Product update(String id, Product product) {
        UUID productId = UUID.fromString(id);
        if (!productRepository.existsById(productId)) {
            throw new IllegalArgumentException("Product not found");
        }
        return productRepository.save(product);
    }

}
