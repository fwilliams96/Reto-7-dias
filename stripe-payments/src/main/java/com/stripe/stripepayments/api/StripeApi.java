package com.stripe.stripepayments.api;

import com.stripe.stripepayments.api.dto.CheckoutRequest;
import com.stripe.stripepayments.api.dto.CheckoutResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/stripe")
public interface StripeApi {

    @PostMapping(value = "/webhook")
    ResponseEntity<Void> webhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String stripeSignature);

    @PostMapping(value = "/checkout")
    ResponseEntity<CheckoutResponse> createCheckout(@RequestBody @Valid CheckoutRequest checkoutRequest);

}
