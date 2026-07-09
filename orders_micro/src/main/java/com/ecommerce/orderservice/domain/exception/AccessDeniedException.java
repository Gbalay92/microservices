package com.ecommerce.orderservice.domain.exception;

public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException() {
        super("Access denied.");
    }
}
