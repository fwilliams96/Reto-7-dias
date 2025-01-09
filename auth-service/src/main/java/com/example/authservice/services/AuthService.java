package com.example.authservice.services;

import com.example.authservice.api.dto.TokenResponse;
import com.example.authservice.api.dto.UserRequest;

public interface AuthService {

    TokenResponse createUser(UserRequest userRequest);

}
