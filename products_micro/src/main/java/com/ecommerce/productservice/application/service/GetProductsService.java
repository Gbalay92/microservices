package com.ecommerce.productservice.application.service;

import com.ecommerce.productservice.domain.model.Product;
import com.ecommerce.productservice.domain.port.in.GetProducts;
import com.ecommerce.productservice.domain.port.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductsService implements GetProducts {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAll(String category) {
        return productRepository.findAll(category);
    }
}
