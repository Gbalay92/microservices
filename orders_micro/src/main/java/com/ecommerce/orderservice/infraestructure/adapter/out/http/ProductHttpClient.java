package com.ecommerce.orderservice.infraestructure.adapter.out.http;

import com.ecommerce.orderservice.domain.exception.ProductNotFoundException;
import com.ecommerce.orderservice.domain.model.ProductInfo;
import com.ecommerce.orderservice.domain.port.out.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductHttpClient implements ProductClient {

    private final RestClient restClient;

    @Override
    public ProductInfo findById(UUID productId) {
        ProductResponse response = restClient.get()
                .uri("/api/products/{id}", productId)
                .retrieve()
                .body(ProductResponse.class);

        if (response == null) {
            throw new ProductNotFoundException(productId.toString());
        }

        return new ProductInfo(response.id(), response.price());
    }
}