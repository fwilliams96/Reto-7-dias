package com.stripe.stripepayments.controllers;

import com.stripe.stripepayments.api.StripeApi;
import com.stripe.stripepayments.api.dto.CheckoutRequest;
import com.stripe.stripepayments.api.dto.CheckoutResponse;
import com.stripe.stripepayments.services.StripeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StripeController implements StripeApi {

    private final StripeService stripeService;

    @Override
    public ResponseEntity<Void> webhook(String payload, String stripeSignature) {
        var event = stripeService.constructEvent(payload, stripeSignature);
        stripeService.manageWebhook(event);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CheckoutResponse> createCheckout(CheckoutRequest checkoutRequest) {
        return ResponseEntity.ok(stripeService.createCheckout(checkoutRequest));
    }
}
