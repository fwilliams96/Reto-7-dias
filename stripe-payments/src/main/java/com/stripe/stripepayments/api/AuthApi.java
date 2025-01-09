package com.stripe.stripepayments.api;

import com.stripe.stripepayments.api.dto.AuthResponseDto;
import com.stripe.stripepayments.api.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/auth")
public interface AuthApi {

    @PostMapping
    ResponseEntity<AuthResponseDto> createUser(@RequestBody @Valid UserRequest userRequest);

}
