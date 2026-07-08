package com.ecommerce.orderservice.infraestructure.adapter.in.web;

import java.util.List;


public record OrderRequest (List<OrderItemRequest> items){
}
