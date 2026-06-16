package com.ecommerce.productservice.application.service;

import com.ecommerce.productservice.domain.model.Product;
import com.ecommerce.productservice.domain.port.in.CreateProduct;
import com.ecommerce.productservice.domain.port.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductService implements CreateProduct {

    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }
}
