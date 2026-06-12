package com.ecommerce.userservice.infrastructure.adapter.out.db;

import com.ecommerce.userservice.domain.model.Address;
import com.ecommerce.userservice.domain.port.out.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AddressJpaAdapter implements AddressRepository {

    private final JpaAddressRepository jpaAddressRepository;

    @Override
    public Address save(Address address) {
        AddressEntity entity = AddressEntity.builder()
                .id(address.getId())
                .userId(address.getUserId())
                .street(address.getStreet())
                .city(address.getCity())
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .isDefault(address.isDefault())
                .build();

        AddressEntity saved = jpaAddressRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Address> findById(UUID id) {
        return jpaAddressRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Address> findByUserId(UUID userId) {
        return jpaAddressRepository.findByUserId(userId).stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        jpaAddressRepository.deleteById(id);
    }

    private Address toDomain(AddressEntity entity) {
        return Address.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .street(entity.getStreet())
                .city(entity.getCity())
                .country(entity.getCountry())
                .zipCode(entity.getZipCode())
                .isDefault(entity.isDefault())
                .build();
    }
}