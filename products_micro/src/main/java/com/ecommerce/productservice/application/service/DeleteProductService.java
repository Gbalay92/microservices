package com.ecommerce.productservice.application.service;

import com.ecommerce.productservice.domain.port.in.DeleteProduct;
import com.ecommerce.productservice.domain.port.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteProductService implements DeleteProduct {

    private final ProductRepository productRepository;

    @Override
    public void delete(String id) {
        UUID uuid = UUID.fromString(id);
        if (!productRepository.existsById(uuid)) {
            throw new IllegalArgumentException("Product not found");
        }
        productRepository.deleteById(uuid);
    }
}
