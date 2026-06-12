package com.ecommerce.userservice.infrastructure.adapter.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaAddressRepository extends JpaRepository<AddressEntity, UUID> {

    List<AddressEntity> findByUserId(UUID userId);
}