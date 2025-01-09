package com.stripe.stripepayments.controllers;

import com.stripe.stripepayments.api.AuthApi;
import com.stripe.stripepayments.api.dto.AuthResponseDto;
import com.stripe.stripepayments.api.dto.UserRequest;
import com.stripe.stripepayments.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    public ResponseEntity<AuthResponseDto> createUser(UserRequest userRequest) {
        return ResponseEntity.ok(authService.createUser(userRequest));
    }
}
