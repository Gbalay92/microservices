package com.ecommerce.orderservice.infraestructure.adapter.in.web;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemResponse (UUID productId, int quantity, BigDecimal unitPrice){
}
