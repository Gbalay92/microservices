package com.ecommerce.productservice.domain.port.in;

import com.ecommerce.productservice.domain.model.Product;

public interface UpdateProduct {
    Product update(String id, Product product);
}
