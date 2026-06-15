package com.ecommerce.userservice.infrastructure.adapter.in.web;

import com.ecommerce.userservice.domain.model.User;
import com.ecommerce.userservice.domain.port.in.AuthenticateUser;
import com.ecommerce.userservice.domain.port.in.RegisterUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegisterUser registerUser;
    private final AuthenticateUser authenticateUser;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
        User user = registerUser.register(
                request.name(),
                request.lastname(),
                request.email(),
                request.password()
        );
        return ResponseEntity.ok(UserResponse.from(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String token = authenticateUser.authenticate(request.email(), request.password());
        return ResponseEntity.ok(token);
    }

    public record RegisterRequest(String name, String lastname, String email, String password) {}
    public record LoginRequest(String email, String password) {}
}