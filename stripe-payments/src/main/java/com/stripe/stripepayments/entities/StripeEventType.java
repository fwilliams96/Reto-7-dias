package com.stripe.stripepayments.entities;

import lombok.Getter;

@Getter
public enum StripeEventType {

    PAYMENT_INTENT_SUCCEED("payment_intent.succeeded"),
    CHECKOUT_SESSION_COMPLETED("checkout.session.completed");

    private final String value;

    StripeEventType(String value) {
        this.value = value;
    }
}
