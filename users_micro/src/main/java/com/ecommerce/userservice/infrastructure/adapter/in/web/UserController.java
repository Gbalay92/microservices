package com.ecommerce.userservice.infrastructure.adapter.in.web;

import com.ecommerce.userservice.domain.model.Address;
import com.ecommerce.userservice.domain.model.User;
import com.ecommerce.userservice.domain.port.in.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final GetUserProfile getUserProfile;
    private final UpdateUserProfile updateUserProfile;
    private final AddAddress addAddress;
    private final GetUserAddresses getUserAddresses;
    private final DeleteAddress deleteAddress;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getProfile(@PathVariable UUID id) {
        return ResponseEntity.ok(UserResponse.from(getUserProfile.getProfile(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateProfile(
            @PathVariable UUID id,
            @RequestBody UpdateProfileRequest request) {
        return ResponseEntity.ok(UserResponse.from(updateUserProfile.update(id, request.name(), request.lastname())));
    }

    @GetMapping("/{id}/addresses")
    public ResponseEntity<List<Address>> getAddresses(@PathVariable UUID id) {
        return ResponseEntity.ok(getUserAddresses.getAddresses(id));
    }

    @PostMapping("/{id}/addresses")
    public ResponseEntity<Address> addAddress(
            @PathVariable UUID id,
            @RequestBody AddAddressRequest request) {
        return ResponseEntity.ok(addAddress.add(id, request.street(), request.city(),
                request.country(), request.zipCode(), request.isDefault()));
    }

    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable UUID addressId) {
        deleteAddress.delete(addressId);
        return ResponseEntity.noContent().build();
    }

    public record UpdateProfileRequest(String name, String lastname) {}
    public record AddAddressRequest(String street, String city, String country, String zipCode, boolean isDefault) {}
}