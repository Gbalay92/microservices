package com.ecommerce.productservice.domain.port.in;

import com.ecommerce.productservice.domain.model.Product;

import java.util.List;

public interface GetProducts {
    List<Product> getAll(String category);
}
