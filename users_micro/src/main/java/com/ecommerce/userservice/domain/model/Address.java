package com.ecommerce.userservice.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class Address {

    private UUID id;
    private UUID userId;
    private String street;
    private String city;
    private String country;
    private String zipCode;
    private boolean isDefault;
}