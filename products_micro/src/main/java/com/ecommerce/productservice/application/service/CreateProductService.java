package com.ecommerce.productservice.application.service;

import com.ecommerce.productservice.domain.model.Product;
import com.ecommerce.productservice.domain.port.in.CreateProduct;
import com.ecommerce.productservice.domain.port.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductService implements CreateProduct {

    private final ProductRepository productRepository;
    private static final Logger log = LoggerFactory.getLogger(CreateProductService.class);

    @Override
    public Product create(Product product) {
        log.info("Creating product: {}", product);
        return productRepository.save(product);
    }
}
