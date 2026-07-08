package com.ecommerce.orderservice.infraestructure.adapter.out;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${products.base-url}")
    private String productsBaseUrl;

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl(productsBaseUrl)
                .build();
    }
}