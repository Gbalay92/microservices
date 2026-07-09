package com.ecommerce.orderservice.infraestructure.adapter.in.web.exception;

public record ErrorResponse(int status, String error, String timestamp) {
}
