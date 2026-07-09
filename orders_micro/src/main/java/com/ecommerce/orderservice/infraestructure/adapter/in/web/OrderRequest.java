package com.ecommerce.orderservice.infraestructure.adapter.in.web;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;


public record OrderRequest (@NotEmpty List<OrderItemRequest> items){
}
