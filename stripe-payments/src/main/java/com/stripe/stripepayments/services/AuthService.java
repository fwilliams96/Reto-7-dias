package com.stripe.stripepayments.services;

import com.stripe.stripepayments.api.dto.AuthResponseDto;
import com.stripe.stripepayments.api.dto.UserRequest;

public interface AuthService {

    AuthResponseDto createUser(UserRequest userRequest);

}
