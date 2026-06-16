package com.ecommerce.productservice.domain.port.in;

import com.ecommerce.productservice.domain.model.Product;

public interface GetProductById {
    Product getById(String id);
}
